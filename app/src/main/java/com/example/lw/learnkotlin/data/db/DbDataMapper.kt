package com.example.lw.learnkotlin.data.db

import com.example.lw.learnkotlin.domin.model.CityForecast
import com.example.lw.learnkotlin.domin.model.DayForecast
import com.example.lw.learnkotlin.domin.model.Forecast

/**
 * Created on 2018/12/5.
 * @author Alan
 */
class DbDataMapper {

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map {

        }
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date.toString(), description, high, low, iconUrl)
    }
}