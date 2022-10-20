package com.example.peter.controller

import com.alibaba.fastjson2.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @GetMapping("setting")
    fun setting() : JSONObject {
        val res = JSONObject()
        res.put("isLogin", true)

        return res
    }
}