package com.example.bitcoin_app

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


interface BitcoinService {
    @GET("listings/latest?CMC_PRO_API_KEY=36bc5ee0-40d7-4fa2-87da-bf391acbe8fd&start=1&limit=10")
    fun getCriptList(): Call<DataList>
    companion object Factory{
        fun create(): BitcoinService{
            val httpLogingInterceptor = HttpLoggingInterceptor()
            httpLogingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLogingInterceptor)
                .build()

//        Log.i("ok", "okHttpClient")

            val retrofit = Retrofit.Builder()
                .baseUrl("https://pro-api.coinmarketcap.com/v1/cryptocurrency/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(BitcoinService :: class.java)
        }
    }
}