package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.bean.ForecastResult
import com.google.gson.Gson
import java.net.URL

/**
 * Created on 2018/12/10.
 * @author Alan
 */
class ForecastServerRequest(val zipCode: Long, val gson: Gson) {

    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val API = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$API&APPID=$APP_ID&zip="
    }

    fun request(): ForecastResult {
        val result = URL(COMPLETE_URL + zipCode).readText()
        return gson.fromJson(result,ForecastResult::class.java)
    }

}