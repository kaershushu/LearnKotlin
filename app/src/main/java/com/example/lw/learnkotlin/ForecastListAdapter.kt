package com.example.lw.learnkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.Forecast
import org.jetbrains.anko.find

/**
 * Created on 2018/11/22.
 * @author Alan
 */
class ForecastListAdapter(private val forecastList: ForecastList, private val onItemClickListener: (Forecast) -> Unit) :
    RecyclerView.Adapter<ForecastListAdapter.VH>() {
    override fun onBindViewHolder(holder: VH, position: Int) {
        with(forecastList[position]) {
            holder.dateView.text = date
            holder.descriptionView.text = description
        }
    }

    override fun getItemCount(): Int = forecastList.size()

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): VH =
        VH(LayoutInflater.from(holder.ctx).inflate(R.layout.layout_adapter, holder, false), onItemClickListener)

    inner class VH(itemView: View, onItemClickListener: (Forecast) -> Unit) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        private val iconView: ImageView
        val dateView: TextView
        val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTemperatureView: TextView

        init {
            iconView = itemView.find(R.id.icon)
            dateView = itemView.find(R.id.date)
            descriptionView = itemView.find(R.id.description)
            maxTemperatureView = itemView.find(R.id.maxTemperature)
            minTemperatureView = itemView.find(R.id.minTemperature)
        }
    }

    public interface OnItemClickListener {
        operator fun invoke(forecast: com.example.lw.learnkotlin.domin.model.Forecast)
    }
}