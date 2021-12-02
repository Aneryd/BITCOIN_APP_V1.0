package com.example.bitcoin_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Headers
import java.net.URL

class MainActivity() : AppCompatActivity() {

    private var recycler_view: RecyclerView? = null

//    val key = "36bc5ee0-40d7-4fa2-87da-bf391acbe8fd"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crypto_layout)

    }

    private fun getBitoc() {

        val textView: TextView = findViewById(R.id.name)
        val symbolView: TextView = findViewById(R.id.symbol)
        val priceView: TextView = findViewById(R.id.money)
        val changePrice: TextView = findViewById(R.id.precent24)

//        val httpLogingInterceptor = HttpLoggingInterceptor()
//        httpLogingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLogingInterceptor)
//            .build()
//
////        Log.i("ok", "okHttpClient")
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://pro-api.coinmarketcap.com/v1/cryptocurrency/")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
////        Log.i("retrofit", "retrofit")
//
//        val service = retrofit.create(BitcoinService::class.java)

        val response = BitcoinService.create().getCriptList()

        response.enqueue (object: Callback<DataList> {
            override fun onResponse(call: Call<DataList>?, response: Response<DataList>?)
            {
                val DataResponse = response!!.body()!!
                for (cripta in DataResponse.data)
                {
                    Log.i("TEST", cripta.name)
                    Log.i("S", cripta.symbol)
                    Log.i("PRICE", cripta.quote.usd.price.toString())
                    Log.i("Change", cripta.quote.usd.percent_change_24h.toString())
                    textView.setText(cripta.name)
                    symbolView.setText(cripta.symbol)
                    priceView.setText(cripta.quote.usd.price.toString())
                    changePrice.setText(cripta.quote.usd.percent_change_24h.toString())
                }
            }

            override fun onFailure(call: Call<DataList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onStart()
    {
        super.onStart()
        getBitoc()
    }
}