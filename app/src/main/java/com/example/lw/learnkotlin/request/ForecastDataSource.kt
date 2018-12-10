package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.Forecast

/**
 * Created on 2018/12/10.
 * @author Alan
 */
interface ForecastDataSource {

    fun requestDayForecast(id: Long): Forecast?

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}