package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.domin.model.Forecast

/**
 * Created on 2018/12/11.
 * @author Alan
 */
class RequestDayForecastCommand(val id: Long, val provider: ForecastProvider = ForecastProvider()) : Request<Forecast> {
    override fun execute(): Forecast = provider.requestForecast(id)
}