package com.example.peter.mapper

import com.example.peter.bean.AddressInfo
import com.example.peter.bean.User
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert

interface AddressMapper {


//    @Insert("INSERT INTO address(desc) VALUES(#{desc})")
//    fun insert(addressInfo: AddressInfo?) : Int

    @Insert("INSERT INTO address(address_name, cell_phone, address_desc, province, city, area, user_id)" +
            " VALUES(#{name}, #{cellphone}, #{desc}, #{province}, #{city}, #{area}, #{userId})")
    fun insert(address: AddressInfo?)

    @Delete("DELETE FROM address WHERE name =#{name}")
    fun delete(name: String?)
}