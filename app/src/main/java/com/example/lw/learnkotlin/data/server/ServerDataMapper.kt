package com.example.lw.learnkotlin.data.server

import com.example.lw.learnkotlin.bean.Forecast
import com.example.lw.learnkotlin.bean.ForecastResult
import com.example.lw.learnkotlin.domin.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.lw.learnkotlin.domin.model.Forecast as ModelForecast

/**
 * Created on 2018/12/10.
 * @author Alan
 */
class ServerDataMapper {
    fun convertToDomain(zipCode: Long, result: ForecastResult) = with(result) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { index, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt, weather[0].description, temperature.max.toInt(), temperature.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}