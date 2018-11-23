package com.example.lw.learnkotlin.request

import java.net.URL

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class OpenWeatherMapStrategy : RequestStrategy {

    private val apiKey = "42f7eabaa769eff49da43ad20e9b0ff4"
    private val cityId = "1796236"
    private val url:String = "https://api.openweathermap.org/data/2.5/forecast?id=$apiKey&appid=$cityId"

    override fun request(): String = URL(url).readText()
}