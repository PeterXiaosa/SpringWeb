package com.example.peter.controller

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.example.peter.bean.Order
import com.example.peter.mapper.OrderMapper
import com.example.peter.mapper.UserMapper
import com.example.peter.util.OrderUtil
import com.example.peter.util.UserUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/order")
class OrderController {

    @Autowired(required = false)
    private val orderMapper: OrderMapper? = null

    @PostConstruct
    fun init() {
        OrderUtil.orderMapper = orderMapper
    }

    @PostMapping(value = ["/makeOrder/{token}"])
    fun makeOrder(@RequestBody orderData:JSONObject, @PathVariable("token") token:String) : JSONObject {
        val res = JSONObject()

        val user = UserUtil.getUserIdByToken(token)
        if(user == null) {
            res["code"] = 1
            res["errmsg"] = "Token用户不存在"
        } else {
            // 将订单信息插入数据库
            OrderUtil.insertOrder(orderData, user.userId)
            res["code"] = 0
        }
        return res
    }

//    @PostMapping(value = ["queryOrder/{token}"])
//    fun queryOrder(@RequestBody jsonObject: JSONObject, @PathVariable("token") token:String) : JSONObject {
//        val res = JSONObject()
//
//        return res
//    }

    @GetMapping(value = ["/getAllOrder/{token}"])
    fun getAllOrder(@PathVariable("token") token:String) : JSONObject {
        val res = JSONObject()

        val user = UserUtil.getUserIdByToken(token)
        if(user == null) {
            res["code"] = 1
            res["errmsg"] = "Token用户不存在"
        } else {
            // 将订单信息插入数据库
            val orderList : List<Order?>? = OrderUtil.getAllOrder(user.userId)
            res["code"] = 0
            res["data"] = JSONArray(orderList)
        }

        return res
    }

    @PostMapping(value = ["/cancelOrder/{token}"])
    fun cancelOrder(@RequestBody jsonObject: JSONObject, @PathVariable("token") token: String) : JSONObject {
        val res = JSONObject()

        val user = UserUtil.getUserIdByToken(token)
        if(user == null) {
            res["code"] = 1
            res["errmsg"] = "Token用户不存在"
        } else {
            // 将订单信息插入数据库
            val orderData = JSON.parseObject(JSONObject.toJSONString(jsonObject["orderData"]), Order::class.java)
            orderData.orderStatus = 3
            orderMapper?.cancelOrder(orderData)
//            val orderList : List<Order?>? = OrderUtil.getAllOrder(user.userId)
            res["code"] = 0
//            res["data"] = orderData

        }

        return res
    }

    @PostMapping(value = ["/queryOrder/{token}"])
    fun queryOrder(@RequestBody jsonObject: JSONObject, @PathVariable("token") token: String) : JSONObject {
        val res = JSONObject()

        val user = UserUtil.getUserIdByToken(token)
        if(user == null) {
            res["code"] = 1
            res["errmsg"] = "Token用户不存在"
        } else {
            val status = jsonObject["status"]
            val orderList = orderMapper?.getOrderByUserId(user.userId)
            val resultList = mutableListOf<Order>()
            orderList?.forEach {
                if (it?.orderStatus == status) {
                    it?.let { it1 -> resultList.add(it1) }
                }
            }
            res["code"] = 0
            res["data"] = resultList
        }

        return res
    }
}