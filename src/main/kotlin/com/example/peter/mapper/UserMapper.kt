package com.example.peter.mapper

import com.example.peter.bean.AddressInfo
import com.example.peter.bean.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

//@Mapper
interface UserMapper {

    @Insert("INSERT INTO user(user_id, openId,token, token_effective_time)" +
            " VALUES(#{userId}, #{openId}, #{token},  #{tokenEffectiveTime})")
    fun insert(userInfo: User?)

    /**
     * column 为数据库字段名，porperty为实体类属性名。
     */
    @Select("SELECT * FROM user")
    @Results(Result(property = "userId", column = "userId"), )
    fun getAll(): List<AddressInfo?>?

    @Select("SELECT * from user where token = #{token}")
//    @Results(Result(property = "userId", column = "user_id"), Result(property = "tokenEffectiveTime", column = "token_effective_time"))
    fun getUserByToken(@Param("token") token : String) : User
}