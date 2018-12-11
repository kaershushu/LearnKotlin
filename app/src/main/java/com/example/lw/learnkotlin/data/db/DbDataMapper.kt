package com.example.lw.learnkotlin.data.db

import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.CityForecast
import com.example.lw.learnkotlin.domin.model.DayForecast
import com.example.lw.learnkotlin.domin.model.Forecast

/**
 * Created on 2018/12/5.
 * @author Alan
 */
class DbDataMapper {

    fun convertToDomain(forecast: CityForecast): ForecastList? = with(forecast) {
        val daily = dailyForecast.map {
            convertDayToDomain(it)
        }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map {
            convertDayFromDomain(id, it)
        }
        com.example.lw.learnkotlin.domin.model.CityForecast(id = id, city = city, country = country, dailyForecast = daily)
    }

    fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date = date.toLong(), description = description, high = high, low = low, iconUrl = iconUrl, cityId = cityId)
    }
}