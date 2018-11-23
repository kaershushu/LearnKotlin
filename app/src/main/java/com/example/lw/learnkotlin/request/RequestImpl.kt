package com.example.lw.learnkotlin.request

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class RequestImpl(val cityId: String) : Request {

    companion object {
        private val APP_ID = ""
        private val URL = ""
        private val CITY_ID = "$cityId"
    }

    override fun request(strategy: RequestStrategy): String = strategy.request()
}