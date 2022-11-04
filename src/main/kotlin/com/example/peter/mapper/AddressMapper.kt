package com.example.peter.mapper

import com.example.peter.bean.AddressInfo
import com.example.peter.bean.User
import org.apache.ibatis.annotations.*

interface AddressMapper {


//    @Insert("INSERT INTO address(desc) VALUES(#{desc})")
//    fun insert(addressInfo: AddressInfo?) : Int

    @Insert("INSERT INTO address(address_name, cell_phone, address_desc, province, city, area, user_id, address_id)" +
            " VALUES(#{name}, #{cellphone}, #{desc}, #{province}, #{city}, #{area}, #{userId}, #{addressIndex})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insert(address: AddressInfo?)

    @Delete("DELETE FROM address WHERE name =#{name}")
    fun delete(name: String?)

    @Select("SELECT * FROM address")
    @Results(id = "addressMap", value = [
        Result(property = "userId", column = "user_id"),
        Result(property = "name", column = "address_name"),
        Result(property = "cellphone", column = "cell_phone"),
        Result(property = "addressIndex", column = "address_id"),
        Result(property = "desc", column = "address_desc")
    ])
    fun getAllAddress(): List<AddressInfo?>?

    @Select("SELECT * from address where id=(select MAX(id) from address)")
    @ResultMap(value = ["addressMap"])
    fun getMaxIdAddress() : AddressInfo

    @Select("SELECT * from address where address_id = #{addressIndex}")
    @ResultMap(value = ["addressMap"])
    fun getAddressByIndex(@Param("addressIndex") addressIndex : Int) : AddressInfo?

    @Update("update address set address_name=#{name},cell_phone=#{cellphone},address_desc=#{desc}," +
            "province=#{province},city=#{city},area=#{area} where user_id=#{userId} and address_id=#{addressIndex}")
    fun updateAddress(address: AddressInfo?)

    @Select("SELECT * FROM address where user_id=#{userId} order by address_id")
    @ResultMap(value = ["addressMap"])
    fun getAddressListByUserId(@Param("userId") userId: String) : List<AddressInfo>
}