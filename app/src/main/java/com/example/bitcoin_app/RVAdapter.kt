package com.example.bitcoin_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val result: List<ListItem>):RecyclerView.Adapter<RVAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView: TextView? = null
        var symbolView: TextView? = null
        var priceView: TextView? = null
        var changePrice: TextView? = null

        init {
            textView = itemView.findViewById(R.id.name)
            symbolView = itemView.findViewById(R.id.symbol)
            priceView = itemView.findViewById(R.id.money)
            changePrice = itemView.findViewById(R.id.precent24)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.crypto_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val response = BitcoinService.create().getCriptList()

        val currentBitoc = result[position]

        holder.textView?.text = currentBitoc.name
        holder.symbolView?.text = currentBitoc.symbol
        holder.priceView?.text = currentBitoc.quote.usd.price.toString()
        holder.changePrice?.text = currentBitoc.quote.usd.percent_change_24h.toString()
        
        Log.i("T1", currentBitoc.name)
    }

    override fun getItemCount(): Int {
        return result.size
    }
}