package com.example.lw.learnkotlin.request

import java.net.URL

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class OpenWeatherMapStrategy(val zipCode: String) : RequestStrategy {

    // http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7&APPID=15646a06818f61f7b8d7823ca833e1ce&zip=94043
    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }

    override fun request(): String = URL(COMPLETE_URL + zipCode).readText()
}