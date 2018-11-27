package com.example.lw.learnkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lw.learnkotlin.domin.ForecastList

/**
 * Created on 2018/11/22.
 * @author Alan
 */
class ForecastListAdapter(private val forecastList: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.VH>() {
    override fun onBindViewHolder(p0: VH, p1: Int) {
        p0.tv.text = forecastList.dailyForecast[p1].description
        p0.tvTemp.text = forecastList.dailyForecast[p1].high.toString()

        with(forecastList.dailyForecast[p1]) {
            p0.tv.text = "$date"
            p0.tvTemp.text = "$description"
        }
    }

    override fun getItemCount(): Int = forecastList.dailyForecast.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH = VH(LayoutInflater.from(p0.context).inflate(R.layout.layout_adapter, p0, false))

    inner class VH(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        var tv: TextView = itemView.findViewById(R.id.tv) as TextView

        var tvTemp: TextView = itemView.findViewById(R.id.tv_temp) as TextView

    }
}