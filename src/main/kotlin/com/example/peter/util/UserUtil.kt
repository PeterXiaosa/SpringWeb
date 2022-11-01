package com.example.peter.util

import com.example.peter.bean.User
import com.example.peter.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import java.text.SimpleDateFormat
import javax.annotation.PostConstruct

class UserUtil {

//    @Autowired(required = false)
//    public var userMapper: UserMapper? = null

//    @PostConstruct
//    fun initMapper() {
//        userMapper = this.userMapperTemp
//        userUtil = this
//    }

    companion object {
        var userUtil : UserUtil? = null
//        var userMapper : UserMapper? = null

        @JvmStatic
        fun getUserIdByToken(token :String): User? {
//            val user = userMapper?.getUserByToken(token)
//            val user = userUtil?.userMapper?.getUserByToken(token)

            return null
        }

    }
}