package com.example.peter.mapper

import com.example.peter.bean.AddressInfo
import com.example.peter.bean.Order
import com.example.peter.bean.Product
import org.apache.ibatis.annotations.*

interface ProductMapper {
    @Insert("INSERT INTO `appdb`.`product`(`product_id`, `name`, `price`, `imageUrl`, `stock_num`, `product_type`)" +
            " VALUES (#{productId}, #{name}, #{price}, #{imageUrl}, #{stockNum}, #{productType})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insert(product: Product?)

    @Select("SELECT * FROM `product`")
    @Results(id = "productMap", value = [
        Result(property = "productId", column = "product_id"),
        Result(property = "stockNum", column = "stock_num"),
        Result(property = "productType", column = "product_type"),
    ])
    fun getAllProduct(): List<Product?>?

    @Select("SELECT * from `product` where product_id=(select MAX(product_id) from `product`)")
    @ResultMap(value = ["productMap"])
    fun getMaxIdProduct() : Product?
}