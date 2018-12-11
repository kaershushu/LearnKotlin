package com.example.lw.learnkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.Forecast
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * Created on 2018/11/22.
 * @author Alan
 */
class ForecastListAdapter(private val forecastList: ForecastList, private val onItemClickListener: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.VH>() {
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindForecast(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size()

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): VH =
            VH(LayoutInflater.from(holder.ctx).inflate(R.layout.layout_adapter, holder, false), onItemClickListener)

    inner class VH(itemView: View, val onItemClickListener: (Forecast) -> Unit) :
            androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        private val iconView: ImageView = itemView.find(R.id.icon)
        private val dateView: TextView = itemView.find(R.id.date)
        private val descriptionView: TextView = itemView.find(R.id.description)
        private val maxTemperatureView: TextView = itemView.find(R.id.maxTemperature)
        private val minTemperatureView: TextView = itemView.find(R.id.minTemperature)

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.get().load(iconUrl).into(iconView)
                dateView.text = forecast.date.toString()
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.onClick { onItemClickListener(forecast) }
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}