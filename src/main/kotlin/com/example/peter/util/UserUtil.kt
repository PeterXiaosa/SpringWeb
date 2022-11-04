package com.example.peter.util

import com.example.peter.bean.User
import com.example.peter.mapper.UserMapper

class UserUtil {

    companion object {
        var userMapper : UserMapper? = null

        @JvmStatic
        fun getUserIdByToken(token: String): User? {
            return userMapper?.getUserByToken(token)
        }

        @JvmStatic
        fun getUserByOpenId(openId: String) : User? {
            return userMapper?.getUserByOpenId(openId)
        }

        @JvmStatic
        fun generateUser(openId:String, sessionKey:String) : User {
            val user = User()
            var id = 1
            userMapper?.getMaxIdUser()?.let {
                id = it.id+1
            }
            user.userId = TokenUtil.generateUserIdSuffix(id)
            user.tokenEffectiveTime = TokenUtil.generateTokenEffectiveTime()
            user.token = TokenUtil.generateToken(user.tokenEffectiveTime)
            user.openId = openId as String
            user.sessionKey = sessionKey as String

            // 将user插入库中。
            userMapper?.insert(user)

            return user
        }
    }
}