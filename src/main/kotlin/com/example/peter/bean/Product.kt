package com.example.peter.bean

import java.math.BigDecimal

data class Product(var productId:Int? = 0, var name:String? ="", var price:BigDecimal?= BigDecimal.ZERO, var imageUrl:String?="",
                    var stockNum:Int? = 0)
