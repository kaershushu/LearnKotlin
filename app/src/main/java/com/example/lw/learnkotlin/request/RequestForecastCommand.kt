package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.domin.ForecastList

/**
 * Created on 2018/12/10.
 * @author Alan
 */
class RequestForecastCommand(private val zipCode: Long, private val forecastProvider: ForecastProvider = ForecastProvider()) : Request<ForecastList> {
    override fun execute(): ForecastList = forecastProvider.requestByZipCode(zipCode, DAYS)

    companion object {
        const val DAYS = 7
    }
}