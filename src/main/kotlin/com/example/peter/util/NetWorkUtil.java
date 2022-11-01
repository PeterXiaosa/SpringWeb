package com.example.peter.util;

import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;

public class NetWorkUtil {
//    private static Map<String, Retrofit> mRetrofitCacheMap = new HashMap<>();
    private static volatile OkHttpClient mOkHttpClient;
//    private static volatile Retrofit.Builder retroBuilder;

    static {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        mOkHttpClient = clientBuilder.build();

//        retroBuilder = new Retrofit.Builder();
    }

//    public static <T> T createHttpService(Class<T> clazz) {
//        return createHttpService("", clazz);
//    }

//    public static <T> T createHttpService(String endpoint, Class<T> clazz) {
//        return createHttpService(endpoint, clazz, null);
//    }

//    public static <T> T createHttpService(Class<T> clazz, LuOKParam param) {
//        return createHttpService("", clazz, param);
//    }

//    public static <T> T createHttpService(String endpoint, Class<T> clazz, LuOKParam param) {
//        if (!isInitialize) {
//            throw new RuntimeException("call initialize() first!");
//        }
//        endpoint = fixBaseUrl(endpoint);
//        Retrofit retrofit = getRetrofitFromCache(endpoint);
//        if (param != null) {
//            retrofit = retrofit.newBuilder().callFactory(createCallFactory(param)).build();
//        }
//        return retrofit.create(clazz);
//    }

}
