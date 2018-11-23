package com.example.lw.learnkotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created on 2018/11/22.
 * @author Alan
 */
class ForecastListAdapter(private val arrays: List<String>) : RecyclerView.Adapter<ForecastListAdapter.VH>() {
    override fun onBindViewHolder(p0: VH, p1: Int) {
        p0.tv.text = arrays[p1]
    }

    override fun getItemCount(): Int = arrays.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH = VH(LayoutInflater.from(p0.context).inflate(R.layout.layout_adapter, p0, false))

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv: TextView = itemView.findViewById(R.id.tv) as TextView

    }
}