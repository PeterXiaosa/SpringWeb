package com.example.peter.controller

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.example.peter.mapper.OrderMapper
import com.example.peter.mapper.UserMapper
import com.example.peter.util.OrderUtil
import com.example.peter.util.UserUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

    @PostMapping(value = ["queryOrder/{token}"])
    fun queryOrder(@RequestBody jsonObject: JSONObject) : JSONObject {
        val res = JSONObject()

        return res
    }
}