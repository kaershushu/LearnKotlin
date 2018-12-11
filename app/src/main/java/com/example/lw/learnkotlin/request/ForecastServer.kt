package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.data.server.ServerDataMapper
import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.Forecast
import com.google.gson.Gson

/**
 * Created on 2018/12/11.
 * @author Alan
 */
class ForecastServer(val serverDataMapper: ServerDataMapper = ServerDataMapper()) : ForecastDataSource {
    override fun requestDayForecast(id: Long): Forecast? = throw IllegalStateException("")

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? = with(ForecastServerRequest(zipCode, Gson()).request()){
        serverDataMapper.convertToDomain(zipCode,this)
    }
}