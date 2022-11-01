package com.example.peter.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class TokenUtil {

    companion object {
        private val TOKEN_LENGTH = 20
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd_hh:mm:ss")
        private val hourFormate = SimpleDateFormat("yyyyMMddhh")
        private val secondsFormate = SimpleDateFormat("yyyyMMddhhmmss")

        @JvmStatic
        fun generateAccessToken(): String {
            val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            val random1 = Random()

            val sb = StringBuffer(dateFormat.format(Date()) + "-")
            for (i in 0..TOKEN_LENGTH) {
                val number = random1.nextInt(str.length)
                val chartAt = str[number]
                sb.append(chartAt)
            }

            return sb.toString()
        }

        @JvmStatic
        fun generateUserIdSuffix(index : Int):String {
            val sb = StringBuffer(hourFormate.format(Date()))
            if (index > 99999) {
                sb.append(index)
            } else {
                var temp = index
                var length = 0
                while (temp>0) {
                    length++
                    temp /= 10
                }
                for (i in 0 until (5-length)) {
                    sb.append("0")
                }
                sb.append(index)
            }
            return sb.toString()
        }

        @JvmStatic
        fun generateTokenEffectiveTime():String {
            val date = Date()
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.add(Calendar.MINUTE, 30)
//            var currentTime = System.currentTimeMillis()
//            currentTime = currentTime.and(30.times(60).times(1000).toLong())
//            val date = Date(currentTime)
            val res = secondsFormate.format(calendar.time)
            calendar.clear()
            return res
        }

        @JvmStatic
        fun generateToken(effectiveTime:String):String {
            val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            val random1 = Random()

            val sb = StringBuffer(effectiveTime)
            for (i in 0..10) {
                val number = random1.nextInt(str.length)
                val chartAt = str[number]
                sb.append(chartAt)
            }

            return sb.toString()
        }

        @JvmStatic
        fun validateTokenEffective(token:String):Boolean {
            // 通过token去数据库查询有效时间验证是否过期。

            return true
        }
    }
}