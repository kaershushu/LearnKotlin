package com.example.lw.learnkotlin.request

import java.net.URL

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class OpenWeatherMapStrategy(val zipCode: String) : RequestStrategy {

    //    http://api.openweathermap.org/data/2.5/weather?q=London
    companion object {
        private const val APP_ID = "42f7eabaa769eff49da43ad20e9b0ff4"
        private const val URL = "https://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&appid=$APP_ID&q="
    }

    override fun request(): String = URL(COMPLETE_URL + zipCode).readText()
}