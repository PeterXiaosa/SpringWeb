package com.example.peter.controller

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.example.peter.bean.AddressInfo
import com.example.peter.mapper.AddressMapper
import com.example.peter.util.AddressUtil
import com.example.peter.util.UserUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
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

    @PostMapping("add/{token}")
    fun addAddress(@RequestBody jsonObject: JSONObject, @PathVariable("token") token:String) : JSONObject {
        val res = JSONObject()

        val user = UserUtil.getUserIdByToken(token)
        if (user == null) {
            res["code"] = 1
            res["errmsg"] = "不存在用户"
        } else {
            val userId = user.userId
            val addressInfo = JSON.parseObject(JSONObject.toJSONString(jsonObject["addressInfo"]), AddressInfo::class.java)
            if (addressInfo.addressIndex == -1) {
                AddressUtil.addNewAddress(jsonObject, userId)
            } else {
                val exitAddressInfo = AddressUtil.getAddressByIndex(addressInfo.addressIndex)
                if (exitAddressInfo == null) {
                    AddressUtil.addNewAddress(jsonObject, userId)
                } else {
                    AddressUtil.updateAddress(jsonObject, exitAddressInfo)
                }
            }
            res["code"] = 0
        }

        return res
    }

    @GetMapping("getAddress/{token}")
    fun getAddressList(@PathVariable("token") token:String) : JSONObject {
        val res = JSONObject()

        val user = UserUtil.getUserIdByToken(token)
        if (user == null) {
            res["code"] = 1
            res["errmsg"] = "未查询到用户"
        } else {
            val list: List<AddressInfo>? = AddressUtil.getAddressList(user.userId)

            if (list == null || list.size == 0) {
                res["code"] = 2
                res["errmsg"] = "未查询到地址信息"
            } else {
                res["code"] = 0
                val array = JSONArray.parseArray(JSON.toJSONString(list))
                res["data"] = array
            }
        }
        return res
    }

    @GetMapping("getDefaultAddress/{token}")
    fun getDefaultAddress(@PathVariable("token") token:String) : JSONObject {
        val res = JSONObject()

        val user = UserUtil.getUserIdByToken(token)
        if (user == null) {
            res["code"] = 1
            res["errmsg"] = "未查询到用户"
        } else {
            val list: List<AddressInfo>? = AddressUtil.getAddressList(user.userId)

            if (list == null || list.size == 0) {
                res["code"] = 0
                res["data"] = ""
            } else {
                res["code"] = 0

                val defaultAddressIndex = user.defaultSelectedAddressIndex
                var addressInfo : AddressInfo? = null
                list.forEach {
                    if (it.addressIndex == defaultAddressIndex) {
                        addressInfo = it
                    }
                }
                res["data"] = JSON.toJSONString(addressInfo)
            }
        }
        return res
    }

}