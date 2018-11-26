package com.example.lw.learnkotlin.request

import com.example.lw.learnkotlin.bean.ForecastResult
import com.google.gson.Gson

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class RequestImpl : Request<ForecastResult> {

    override fun execute(strategy: RequestStrategy): ForecastResult {
        val json = strategy.request()
        return Gson().fromJson(json, ForecastResult::class.java)
    }
}