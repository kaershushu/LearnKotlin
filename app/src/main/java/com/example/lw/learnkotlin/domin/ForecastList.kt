package com.example.lw.learnkotlin.domin

import com.example.lw.learnkotlin.domin.model.Forecast

data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>) {
    operator fun get(position: Int) = dailyForecast[position]

    fun size() = dailyForecast.size
}