package com.example.peter.mapper

import com.example.peter.bean.AddressInfo
import com.example.peter.bean.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

//@Mapper
interface UserMapper {

    @Insert("INSERT INTO user(user_id, openId, token, session_key, token_effective_time, default_selected_address_index)" +
            " VALUES(#{userId}, #{openId}, #{token}, #{sessionKey}, #{tokenEffectiveTime}, #{defaultSelectedAddressIndex})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insert(userInfo: User?)

    /**
     * column 为数据库字段名，porperty为实体类属性名。
     */
    @Select("SELECT * FROM user")
    @Results(id = "userMap", value = [
        Result(property = "userId", column = "user_id"),
        Result(property = "tokenEffectiveTime", column = "token_effective_time"),
        Result(property = "sessionKey", column = "session_key"),
        Result(property = "defaultSelectedAddressIndex", column = "default_selected_address_index")
    ])
    fun getAllUser(): List<User?>?

    @Select("SELECT * from user where token = #{token}")
    @ResultMap(value = ["userMap"])
    fun getUserByToken(@Param("token") token : String) : User?

    @Select("SELECT * from user where openId = #{openId}")
    @ResultMap(value = ["userMap"])
    fun getUserByOpenId(@Param("openId") openId : String) : User?

    @Select("SELECT * from user where id=(select MAX(id) from user)")
    @ResultMap(value = ["userMap"])
    fun getMaxIdUser() : User?
}