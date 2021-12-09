package com.example.bitcoin_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity() : AppCompatActivity() {
    var recycler_view: RecyclerView? = null
//    internal lateinit var adapter: RVAdapter

    lateinit var db: BitcoinService

//    val key = "36bc5ee0-40d7-4fa2-87da-bf391acbe8fd"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recycler_view = findViewById(R.id.recyclerView)
//        recycler_view?.setAdapter = RVAdapter
        recycler_view?.layoutManager = LinearLayoutManager(this)
//        recycler_view?.adapter = RVAdapter(getBitoc())
//        recycler_view.adapter = RVAdapter(getBitoc())

    }

    private fun getBitoc(cripta: List<ListItem>){
        val textView: TextView? = findViewById(R.id.name)
        val symbolView: TextView? = findViewById(R.id.symbol)
        val priceView: TextView? = findViewById(R.id.money)
        val changePrice: TextView? = findViewById(R.id.precent24)

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
//        doAsync {
//            val list_db = db.getCriptList()
//            runOnUiThread {
//                recycler_view.apply{
//                    list_db
//                }
//            }
//        }

        val response = BitcoinService.create().getCriptList()


        response.enqueue(object : Callback<DataList> {
            override fun onResponse(call: Call<DataList>?, response: Response<DataList>?) {
                val DataResponse = response!!.body()!!
                for (cripta in DataResponse.data) {
                    Log.i("TEST", cripta.name)
                    Log.i("S", cripta.symbol)
                    Log.i("PRICE", cripta.quote.usd.price.toString())
                    Log.i("Change", cripta.quote.usd.percent_change_24h.toString())
//                    test = cripta.name
                    textView?.setText(cripta.name)
                    symbolView?.setText(cripta.symbol)
                    priceView?.setText(cripta.quote.usd.price.toString())
                    changePrice?.setText(cripta.quote.usd.percent_change_24h.toString())
                }
            }

            override fun onFailure(call: Call<DataList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

//    private fun setBitoc(){
//        doAsync {
//            val list_bit = BitcoinService.create().getCriptList()
//            runOnUiThread{
//                recycler_view?.apply{
//                    adapter = RVAdapter(
//                        data,
//                        this@MainActivity
//                    )
//                }
//            }
//        }
//    }

    override fun onStart() {
        super.onStart()
        getBitoc()
    }
}