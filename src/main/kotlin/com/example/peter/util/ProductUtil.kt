package com.example.peter.util

import com.example.peter.bean.Product
import com.example.peter.mapper.OrderMapper
import com.example.peter.mapper.ProductMapper

class ProductUtil {
    companion object {
        var productMapper: ProductMapper? = null

        fun getAllProductMap() : MutableMap<String, MutableList<Product?>> {
            val map = mutableMapOf<String,MutableList<Product?>>()
            val productList = productMapper?.getAllProduct()

            productList?.forEach {
                val typeName = it?.productType
                if (map.containsKey(typeName)) {
                    val list = map[typeName]
                    list?.add(it)
                    typeName?.let { it1 -> list?.let { it2 -> map.put(it1, it2) } }
                } else {
                    val list = mutableListOf<Product?>()
                    list.add(it)
                    typeName?.let { it1 -> map.put(it1, list) }
                }
            }
            return map
        }
    }
}