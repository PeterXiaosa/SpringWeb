package com.example.peter.util

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import com.example.peter.bean.AddressInfo
import com.example.peter.mapper.AddressMapper

class AddressUtil {

    companion object {
        var addressMapper : AddressMapper? = null

        @JvmStatic
        fun addNewAddress(jsonObject: JSONObject, userId:String) : AddressInfo{
            val addressMap: LinkedHashMap<String, String> = jsonObject["addressInfo"] as LinkedHashMap<String, String>
            val json = JSONObject(addressMap)
            val addressInfo = JSON.parseObject(json.toString(), AddressInfo::class.java)
            addressInfo.userId = userId
            var addressIndex = 0
            addressMapper?.getMaxIdAddress()?.let {
                addressIndex = it.addressIndex + 1
            }
            addressInfo.addressIndex = addressIndex
            addressMapper?.insert(addressInfo)
            return addressInfo
        }

        @JvmStatic
        fun getAddressByIndex(addressIndex: Int): AddressInfo? {
            return addressMapper?.getAddressByIndex(addressIndex)
        }

        @JvmStatic
        fun updateAddress(jsonObject: JSONObject, oldAddressInfo: AddressInfo) {
            val addressMap: LinkedHashMap<String, String> = jsonObject["addressInfo"] as LinkedHashMap<String, String>
            val json = JSONObject(addressMap)
            val addressInfo = JSON.parseObject(json.toString(), AddressInfo::class.java)

            addressInfo.userId = oldAddressInfo.userId
            addressInfo.addressIndex = oldAddressInfo.addressIndex

            addressMapper?.updateAddress(addressInfo)
        }

        @JvmStatic
        fun getAddressList(userId: String) : List<AddressInfo>? {
            return addressMapper?.getAddressListByUserId(userId)
        }
    }
}