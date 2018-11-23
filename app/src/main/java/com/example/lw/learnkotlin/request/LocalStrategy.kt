package com.example.lw.learnkotlin.request

import java.net.URL

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class LocalStrategy : RequestStrategy {

    val cityId = "101020100"
    val url = "http://t.weather.sojson.com/api/weather/city/$cityId"

    override fun request(): String = URL(url).readText()
}
