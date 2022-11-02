package com.example.peter.bean

import java.util.*

data class User(var id:Int = 0, var userId: String = "", var openId: String = "", var sessionKey:String = "", var token: String = "",
                var tokenEffectiveTime: String = "", var defaultSelectedAddressIndex:Int = 0)
