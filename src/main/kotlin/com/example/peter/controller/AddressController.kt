package com.example.peter.controller

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import com.example.peter.bean.AddressInfo
import com.example.peter.mapper.AddressMapper
import com.example.peter.util.AddressUtil
import com.example.peter.util.UserUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/address")
class AddressController {

    @Autowired(required = false)
    private val addressMapper: AddressMapper? = null

    @PostConstruct
    fun init() {
        AddressUtil.addressMapper = addressMapper
    }

    @PostMapping("/add")
//    fun addAddress(@RequestBody addressInfo: AddressInfo?) : JSONObject {
    fun addAddress(@RequestBody jsonObject: JSONObject) : JSONObject {
        val res = JSONObject()
        res["order_id"] = 1

        val token = jsonObject["token"]
//        val user = UserUtil1.getUserIdByToken(token as String)

        val addressMap: LinkedHashMap<String, String> = jsonObject["addressInfo"] as LinkedHashMap<String, String>
        val json = JSONObject(addressMap)
        val addressInfo = JSON.parseObject(json.toString(), AddressInfo::class.java)
//        addressInfo.userId = user?.userId

        try {
//            addressMapper?.delete(addressInfo?.name)
            addressMapper?.insert(addressInfo)
        } catch (e:Exception) {
            e.printStackTrace()
        }

        return res
    }
}