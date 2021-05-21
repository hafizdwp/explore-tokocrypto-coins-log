package com.hafizdwp.explore_tokocrypto_coins_log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_item.view.*
import java.text.NumberFormat
import java.util.*

/**
 * @author hafizdwp
 * 20/05/2021
 **/
class MainAdapter() : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val coins = arrayListOf<Coin>()


    fun updateCoins(coins: List<Coin>) {
        this.coins.clear()
        this.coins.addAll(coins)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    override fun getItemCount(): Int {
//        return coins.size
        return if (coins.size > 3) 3
        else coins.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(coin: Coin) {
            itemView.apply {
                Picasso.get().load(coin.image)
                        .into(img_icon)

                text_coin_symbol.text = coin.symbol.toUpperCase(Locale.ROOT)
                text_coin_name.text = coin.name
                text_price.text = coin.current_price.toString()

                val format = NumberFormat.getInstance().apply {
                    maximumFractionDigits = 0
                    currency = Currency.getInstance("IDR")
                }
                val rupiahFinal = format.format(coin.current_price_idr)

                text_price_rupiah.text = "Rp $rupiahFinal"
            }
        }
    }


}