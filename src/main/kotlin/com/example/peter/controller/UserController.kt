package com.example.peter.controller

import com.alibaba.fastjson2.JSONObject
import com.example.peter.bean.User
import com.example.peter.mapper.UserMapper
import com.example.peter.util.TokenUtil
import com.example.peter.util.UserUtil
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap
import javax.annotation.PostConstruct


@RestController
@RequestMapping("/user")
class UserController {
    private val APPID = "wx6963fddc9ae60e66"
    private val SECRET = "5d310c4e8a6f3665e90d5e250ae3c8e9"
    private val userIdCache = ConcurrentHashMap<String, Int>()
    private var mClient : OkHttpClient? = null


    @Autowired(required = false)
    private val userMapper: UserMapper? = null

    @PostConstruct
    fun init() {
        UserUtil.userMapper = userMapper
    }

    @GetMapping("setting")
    fun setting() : JSONObject {
        val res = JSONObject()
        res.put("isLogin", true)

        return res
    }

    @PostMapping("/login")
    fun login(@RequestBody data: JSONObject): JSONObject {

        val res = JSONObject()
        val code = data.getString("code")

        if(mClient == null) {
            mClient = OkHttpClient()
        }

        val request = Request.Builder().url("https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=wx6963fddc9ae60e66&secret=5d310c4e8a6f3665e90d5e250ae3c8e9&js_code=${code}&grant_type=authorization_code")
            .build()
        val response = mClient?.newCall(request)?.execute()
        val content = response?.body?.string()
        val wxJSONObject = JSONObject.parseObject(content)

        if (wxJSONObject["errcode"] == null) {
            val openId = wxJSONObject["openid"] as String
            val sessionKey = wxJSONObject["session_key"] as String
            var user = UserUtil.getUserByOpenId(openId)
            if (user == null) {
                user = UserUtil.generateUser(openId, sessionKey)
            }

            res["code"] = 0
            res["userId"] = user.userId
            res["tokenEffectiveTime"] = user.tokenEffectiveTime
            res["token"] = user.token
            res["defaultSelectAddressIndex"] = user.defaultSelectedAddressIndex
        } else {
            val errCode = wxJSONObject["errcode"]
            val errMsg = wxJSONObject["errmsg"]
            res["code"] = errCode
            res["errmsg"] = errMsg
        }

        return res
    }
}