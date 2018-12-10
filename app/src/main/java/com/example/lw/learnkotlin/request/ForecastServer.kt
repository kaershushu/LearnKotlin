package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.data.db.ForecastDb
import com.example.lw.learnkotlin.data.server.ServerDataMapper
import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.Forecast

/**
 * Created on 2018/12/10.
 * @author Alan
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(), val forcastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = RequestImpl().execute(OpenWeatherMapStrategy(zipCode = zipCode.toString()))
        val converted = dataMapper.convertToDomain(zipCode, result)
        forcastDb.saveForecast(converted)
        return forcastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}