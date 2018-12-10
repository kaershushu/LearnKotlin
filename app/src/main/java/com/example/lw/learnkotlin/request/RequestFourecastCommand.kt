package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.domin.ForecastList

/**
 * Created on 2018/12/10.
 * @author Alan
 */
class RequestFourecastCommand(val zipCode: Long, val forcastProvider: ForecastProvider = ForecastProvider()) : Request<ForecastList?> {
    override fun execute(strategy: RequestStrategy): ForecastList? {
        return forcastProvider.requestByZipCode(zipCode, DAYS)
    }

    companion object {
        val DAYS = 7
    }
}