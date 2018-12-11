package com.example.lw.learnkotlin.data.server

import com.example.lw.learnkotlin.bean.ForecastResult
import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.DayForecast

/**
 * Created on 2018/12/10.
 * @author Alan
 */
class ServerDataMapper {
    fun convertToDomain(zipCode: Long, result: ForecastResult) = with(result) {
        ForecastList(zipCode, city.name, city.country, list)
    }

//    fun convertToDayForecast(): DayForecast {
//
//    }
}