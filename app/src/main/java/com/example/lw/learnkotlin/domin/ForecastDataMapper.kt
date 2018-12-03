package com.example.lw.learnkotlin.domin

import com.example.lw.learnkotlin.bean.Forecast
import com.example.lw.learnkotlin.domin.model.Forecast as ModelForecast
import com.example.lw.learnkotlin.bean.ForecastResult
import java.text.DateFormat
import java.util.*

public class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map {
            convertForecastItemToDomain(it)
        }
    }

    private fun convertForecastItemToDomain(forecast: com.example.lw.learnkotlin.bean.Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description, forecast.temperature.max.toInt(), forecast.temperature.min.toInt(), generateUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateUrl(iconCode:String):String = "http://openweathermap.org/img/w/$iconCode.png"
}