package com.example.peter.controller

import com.alibaba.fastjson2.JSONObject
import com.example.peter.bean.User
import com.example.peter.mapper.UserMapper
import com.example.peter.util.TokenUtil
import com.example.peter.util.UserUtil
import com.example.peter.util.UserUtil1
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap


@RestController
@RequestMapping("/user")
class UserController {
    private val APPID = "wx6963fddc9ae60e66"
    private val SECRET = "5d310c4e8a6f3665e90d5e250ae3c8e9"
    private val userIdCache = ConcurrentHashMap<String, Int>()


//    @Autowired(required = false)
//    private val userMapper: UserMapper? = null

    @GetMapping("setting")
    fun setting() : JSONObject {
        val res = JSONObject()
        res.put("isLogin", true)

        return res
    }

    @PostMapping("/login")
    fun login(@RequestBody data: JSONObject): JSONObject {
//        UserUtil.userMapper = userMapper

        val res = JSONObject()
        val code = data.getString("code")

        val client = OkHttpClient()
        val request = Request.Builder().url("https://api.weixin.qq.com/sns/jscode2session?appid=wx6963fddc9ae60e66&secret=5d310c4e8a6f3665e90d5e250ae3c8e9&js_code=0834jO0w35c3rZ2SWK0w3lFBT824jO0C&grant_type=authorization_code")
            .build()
//        val response = client.newCall(request).execute()

        val openId = "oorlI6-Bmk7_a8T25F--ykOV7WWo"

        val user = User()
        user.openId = openId
        user.userId = TokenUtil.generateUserIdSuffix(1)
        user.tokenEffectiveTime = TokenUtil.generateTokenEffectiveTime()
        user.token = TokenUtil.generateToken(user.tokenEffectiveTime)

        // mapper为空
        // 将user插入库中。
        UserUtil1.userUtil1.userMapper.insert(user)

        //
        res["userId"] = user.userId
        res["tokenEffectiveTime"] = user.tokenEffectiveTime
        res["token"] = user.token
        return res
    }
}