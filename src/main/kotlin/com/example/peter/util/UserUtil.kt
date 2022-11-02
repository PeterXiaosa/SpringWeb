package com.example.peter.util

import com.example.peter.bean.User
import com.example.peter.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import java.text.SimpleDateFormat
import javax.annotation.PostConstruct

class UserUtil {

//    @Autowired(required = true)
//    public var userMapper: UserMapper? = null
//
//    @PostConstruct
//    fun initMapper() {
//        userUtil = this
//    }

    companion object {
//        var userUtil : UserUtil? = null
        var userMapper : UserMapper? = null

        @JvmStatic
        fun getUserIdByToken(token :String): User? {
//            val user = userMapper?.getUserByToken(token)
            val user = userMapper?.getUserByToken(token)

            return null
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