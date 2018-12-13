package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.data.db.ForecastDb
import com.example.lw.learnkotlin.data.server.ServerDataMapper
import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.Forecast
import com.google.gson.Gson

/**
 * Created on 2018/12/11.
 * @author Alan
 */
class ForecastServer(private val serverDataMapper: ServerDataMapper = ServerDataMapper(), private val db: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestDayForecast(id: Long): Forecast? = throw IllegalStateException("")

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? = with(ForecastServerRequest(zipCode, Gson()).request()) {
        val forecastList = serverDataMapper.convertToDomain(zipCode,this)
        db.saveForecast(forecastList)
        forecastList
    }
}