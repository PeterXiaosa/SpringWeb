package com.example.peter.controller

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.example.peter.bean.Product
import com.example.peter.mapper.OrderMapper
import com.example.peter.mapper.ProductMapper
import com.example.peter.util.OrderUtil
import com.example.peter.util.ProductUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired(required = false)
    private val productMapper: ProductMapper? = null

    @PostConstruct
    fun init() {
        ProductUtil.productMapper = productMapper
    }

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
        jsonObject["code"] = 0
        return jsonObject
    }

    /**
     * list:[{"name:"目录1", "food":[{"id":"0", "name":"commodity1", "price":"10"}] }],promotion:[],
     */
    @GetMapping("/list")
    fun productList() : JSONObject {
        val res = JSONObject()
        val listArray = JSONArray()

        val productMap :  MutableMap<String, MutableList<Product?>> = ProductUtil.getAllProductMap()

        productMap.keys.forEach {
            val productList = productMap[it]
            val jsonArray : JSONArray = JSONArray.parseArray(JSON.toJSONString(productList))

            val categoryJson = JSONObject()
            categoryJson.put("type", it)
            categoryJson.put("list", jsonArray)
            listArray.add(categoryJson)
        }

        res["productList"] = listArray

        val promotionArray = JSONArray()
        val promotion1Object = JSONObject()
        promotion1Object.put("k", 1000000)
        promotion1Object.put("v", 10)
        promotionArray.add(promotion1Object)
        res.put("promotion", promotionArray)
        res["code"] = 0

        return res
    }

    /**
     * 生成预订单信息, 检查库存信息。
     */
    @PostMapping("/checkProductNum")
    fun makePreOrder(@RequestBody productArray: JSONArray) : JSONObject {
        val res = JSONObject()
        // 检查数据库中库存，不够的话返回非0
        res["code"] = 0
        return res
    }

    @GetMapping("/order")
    fun getOrderId(@RequestParam(required = true, defaultValue = "", value = "id")orderId : Long) : JSONObject{
        val res = JSONObject()
        val orderFoodArray = JSONArray()
        val orderFood1 = JSONObject()
        orderFood1["image_url"] = "../../images/duola.png"
        orderFood1["name"] = "物品1"
        orderFood1["price"] = 10
        orderFood1["number"] = 1
        orderFoodArray.add(orderFood1)
        val orderFood2 = JSONObject()
        orderFood2["image_url"] = "../../images/duola.png"
        orderFood2["name"] = "物品2"
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

    @GetMapping("addProduct")
    fun addProduct() : JSONObject {
        val res = JSONObject()


        res["code"] = 0
        return res
    }
}
