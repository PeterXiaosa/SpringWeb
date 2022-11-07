package com.example.peter.util

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.example.peter.bean.Order
import com.example.peter.mapper.OrderMapper
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class OrderUtil {

    companion object {
        var orderMapper: OrderMapper? = null
        private val secondsFormate = SimpleDateFormat("yyyyMMddhhmmss")
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

        @JvmStatic
        fun insertOrder(orderData: JSONObject, userId:String) {
            val addressJson = JSONObject.parseObject(orderData["address"] as String)
            val orderListArray = JSONArray.parseArray(orderData.getString("orderList"))

            val order = Order()
            order.userId = userId
            order.orderId = generateOrderId(userId)
            order.orderStatus = 0
            order.orderImage = orderListArray.getJSONObject(0)["imageUrl"] as String
            order.orderPrice = caculateOrderPrice(orderListArray)
            order.productSum = calculateOrderCount(orderListArray)
            order.productList = generateProductListArray(orderListArray)
            order.orderTime = dateFormat.format(Date())
            order.receiveAddressIndex = addressJson.getIntValue("addressIndex")

            
            orderMapper?.insert(order)
        }

        @JvmStatic
        fun getAllOrder(userId:String) : List<Order?>?{
            val orderList : List<Order?>? = orderMapper?.getOrderByUserId(userId)
            return orderList
        }

        private fun calculateOrderCount(orderListArray: JSONArray?): Int {
            var res = 0
            orderListArray?.forEach {
                res += (it as JSONObject).getIntValue("number")
            }
            return res
        }

        private fun caculateOrderPrice(orderListArray: JSONArray?): BigDecimal {
            var res = BigDecimal.ZERO
            orderListArray?.forEach {
                res = res.add(BigDecimal.valueOf((it as JSONObject).getDouble("price"))
                        .multiply(BigDecimal.valueOf((it as JSONObject).getDouble("number"))))
            }
            return res
        }

        /**
         * 时间戳+UserId+自增id
         */
        private fun generateOrderId(userId: String) : String {
            val timestamp = secondsFormate.format(Date())
            val maxIdOrder = orderMapper?.getMaxIdOrder()
            var id = 1
            maxIdOrder?.let {
                id = it.id+1
            }
            return timestamp+userId+id
        }

        private fun generateProductListArray(jsonArray: JSONArray) : String {
            jsonArray.forEach {
                (it as JSONObject).remove("name")
                (it as JSONObject).remove("price")
                (it as JSONObject).remove("imageUrl")
            }
            return jsonArray.toJSONString()
        }
    }
}