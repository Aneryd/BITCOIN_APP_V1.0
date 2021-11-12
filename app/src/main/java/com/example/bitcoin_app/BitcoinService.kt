package com.example.bitcoin_app

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Headers


interface BitcoinService {
//    fun getCriptList(): Call<List<Cripta>>
//    @GET("limit=5")
//    @GET("application/json?CMC_PRO_API_KEY=36bc5ee0-40d7-4fa2-87da-bf391acbe8fd")
//    @Headers("X-CMC_PRO_API_KEY: 36bc5ee0-40d7-4fa2-87da-bf391acbe8fd")
//    @GET("CMC_PRO_API_KEY: 36bc5ee0-40d7-4fa2-87da-bf391acbe8fd")
    @GET("listings/latest?CMC_PRO_API_KEY=36bc5ee0-40d7-4fa2-87da-bf391acbe8fd&start=1&limit=10")
    fun getCriptList(): Call<DataList>
}