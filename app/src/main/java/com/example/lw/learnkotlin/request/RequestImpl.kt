package com.example.lw.learnkotlin.request

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class RequestImpl(val cityId: String) : Request {

    companion object {
        private val APP_ID = "42f7eabaa769eff49da43ad20e9b0ff4"
        private val URL = "https://samples.openweathermap.org/data/2.5/forecast?id="
        private val CITY_ID = ""
        private val COMPLET_URL = "$URL$CITY_ID&appid=$APP_ID"
    }

    override fun request(strategy: RequestStrategy): String = strategy.request()
}