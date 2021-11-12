package com.example.bitcoin_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class MainActivity : AppCompatActivity() {

    private var recycler_view: RecyclerView? = null

    val key = "36bc5ee0-40d7-4fa2-87da-bf391acbe8fd"
//    val url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/"

    lateinit var questApi: BitcoinService

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun getBitoc() {

        val textView: TextView = findViewById(R.id.test)
        val symbolView: TextView = findViewById(R.id.symbol)
        val priceView: TextView = findViewById(R.id.price)

        val httpLogingInterceptor = HttpLoggingInterceptor()
        httpLogingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLogingInterceptor)
            .build()

        Log.i("ok", "okHttpClient")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/v1/cryptocurrency/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        Log.i("retrofit", "retrofit")

        val service = retrofit.create(BitcoinService::class.java)

        val response = service.getCriptList()

        response.enqueue (object: Callback<DataList> {
            override fun onResponse(call: Call<DataList>?, response: Response<DataList>?)
            {
                val DataResponse = response!!.body()!!
                for (cripta in DataResponse.data)
                {
                    Log.i("TEST", cripta.name)
                    textView.setText(cripta.name)
                    symbolView.setText(cripta.symbol)
                    priceView.setText(cripta.price.toString())
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