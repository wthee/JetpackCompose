package cn.wthee.jetpackcompose.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreater {

    /**
     * 创建服务
     */
    fun <T> create(serviceClass: Class<T>): T {
        val builder = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())

        return builder.build().create(serviceClass)
    }
}