package com.example.peter.bean

import java.math.BigDecimal

data class Order (var id:Int = 0, var userId:String = "", var orderId:String = "", var orderStatus:Int = 0, var productList:String = "",
                  var orderPrice:BigDecimal = BigDecimal.ZERO, var productSum:Int = 0, var orderTime:String = "", var payTime:String="",
                  var receiveAddressIndex:Int = 0, var orderImage:String="")