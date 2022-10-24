package com.example.peter.controller

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/food")
class FoodController {

    @GetMapping("/index")
    fun foodIndex() : JSONObject{
        val jsonObject = JSONObject()
        val array = JSONArray()
        array.add("../../images/duola.png")
        array.add("../../images/duola.png")
        array.add("../../images/duola.png")
        array.add("../../images/duola.png")
        val categoryArray = JSONArray()
        categoryArray.add("../../images/duola.png")
        categoryArray.add("../../images/duola.png")
        categoryArray.add("../../images/duola.png")

        jsonObject.put("img_swiper", array)
        jsonObject.put("img_ad", "../../images/duola.png")
        jsonObject.put("img_category", categoryArray)
        return jsonObject
    }

    /**
     * list:[{"name:"目录1", "food":[{"id":"0", "name":"commodity1", "price":"10"}] }],promotion:[],
     */
    @GetMapping("/list")
    fun foodList() : JSONObject {
        val res = JSONObject()
        val listArray = JSONArray()
        val category1Json = JSONObject()
        category1Json.put("name", "一部")
        val category1FoodArray = JSONArray()
        val category1Food1Json = JSONObject()
        category1Food1Json["id"] = 0
        category1Food1Json["image_url"] = "../../images/10袋小儿咽扁颗粒.png"
        category1Food1Json.put("name", "10袋小儿咽扁颗粒")
        category1Food1Json.put("price", 10)
        category1FoodArray.add(category1Food1Json)
        val category1Food2Json = JSONObject()
        category1Food2Json.put("id", 1)
        category1Food2Json["image_url"] = "../../images/130片蚝贝钙咀嚼片小盒.png"
        category1Food2Json.put("name", "130片蚝贝钙咀嚼片小盒")
        category1Food2Json.put("price", 20)
        category1FoodArray.add(category1Food2Json)
        category1Json.put("food", category1FoodArray)
        val category2Json = JSONObject()
        category2Json.put("name", "二部")
        val category2FoodArray = JSONArray()
        val category2Food2Json = JSONObject()
        category2Food2Json.put("id", 0)
        category2Food2Json["image_url"] = "../../images/10片阿莫西林分散片.png"
        category2Food2Json.put("name", "10片阿莫西林分散片")
        category2Food2Json.put("price", 30)
        category2FoodArray.add(category2Food2Json)
        val category2Food1Json = JSONObject()
        category2Food1Json.put("id", 1)
        category2Food1Json["image_url"] = "../../images/12ml萘敏维滴眼液a.png"
        category2Food1Json.put("name", "12ml萘敏维滴眼液a")
        category2Food1Json.put("price", 40)
        category2FoodArray.add(category2Food1Json)
        category2Json.put("food", category2FoodArray)
        listArray.add(category1Json)
        listArray.add(category2Json)

        res["list"] = listArray

        val promotionArray = JSONArray()
        val promotion1Object = JSONObject()
        promotion1Object.put("k", 50)
        promotion1Object.put("v", 10)
        promotionArray.add(promotion1Object)
        res.put("promotion", promotionArray)

        return res
    }

    @PostMapping("/order")
    fun foodList(orderList : JSONArray) : JSONObject {
        val res = JSONObject()
        res["order_id"] = 1

        return res
    }

    @GetMapping("/order")
    fun getOrderId(@RequestParam(required = true, defaultValue = "", value = "id")orderId : Long) : JSONObject{
        val res = JSONObject()
        val orderFoodArray = JSONArray()
        val orderFood1 = JSONObject()
        orderFood1["image_url"] = "../../images/duola.png"
        orderFood1["name"] = "咳嗽糖浆1"
        orderFood1["price"] = 10
        orderFood1["number"] = 1
        orderFoodArray.add(orderFood1)
        val orderFood2 = JSONObject()
        orderFood2["image_url"] = "../../images/duola.png"
        orderFood2["name"] = "咳嗽糖浆2"
        orderFood2["price"] = 20
        orderFood2["number"] = 1
        orderFoodArray.add(orderFood2)

        res["order_food"] = orderFoodArray
        res["promotion"] = 50
        res["price"] = 80
        res["id"] = 1
        return res
    }

    @PostMapping("/pay")
    fun pay(id : String?) : JSONObject {
        val res = JSONObject()
        res["id"] = 1

        return res
    }
}
