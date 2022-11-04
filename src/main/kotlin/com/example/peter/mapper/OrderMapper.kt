package com.example.peter.mapper

import com.example.peter.bean.Order
import com.example.peter.bean.User
import org.apache.ibatis.annotations.*

interface OrderMapper {
    @Insert("INSERT INTO `order`(user_id, order_id, order_status, product_list, order_price, product_sum, order_time" +
            ", pay_time, receive_address)" +
            " VALUES(#{userId}, #{orderId}, #{orderStatus}, #{productList}, #{orderPrice}, #{productSum}, #{orderTime}" +
            ", #{payTime}, #{receiveAddressIndex})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insert(order: Order?)

    @Select("SELECT * FROM `order`")
    @Results(id = "orderMap", value = [
        Result(property = "userId", column = "user_id"),
        Result(property = "orderId", column = "order_id"),
        Result(property = "orderStatus", column = "order_status"),
        Result(property = "productList", column = "product_list"),
        Result(property = "order_price", column = "orderPrice"),
        Result(property = "product_sum", column = "productSum"),
        Result(property = "order_time", column = "orderTime"),
        Result(property = "pay_time", column = "payTime"),
        Result(property = "receive_address", column = "receiveAddressIndex"),
    ])
    fun getAllOrder(): List<Order?>?

    @Select("SELECT * from `order` where order_id=#{orderId}")
    @ResultMap(value = ["orderMap"])
    fun getOrderByOrderId(@Param("orderId") orderId:String) : Order

    @Select("SELECT * from `order` where user_id=#{userId}")
    @ResultMap(value = ["orderMap"])
    fun getOrderByUserId(@Param("userId") userId:String) : List<Order?>?

    @Select("SELECT * from `order` where id=(select MAX(id) from `order`)")
    @ResultMap(value = ["orderMap"])
    fun getMaxIdOrder() : Order?
}