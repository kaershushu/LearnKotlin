package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.domin.ForecastList

/**
 * Created on 2018/12/10.
 * @author Alan
 */
class ForecastRequest(private val indexInList: Long, private val forecastProvider: ForecastProvider = ForecastProvider()) : Request<ForecastList> {
    override fun execute(): ForecastList = forecastProvider.requestByZipCode(indexInList, DAYS)

    companion object {
        const val DAYS = 7
    }
}