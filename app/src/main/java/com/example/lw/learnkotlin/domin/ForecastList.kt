package com.example.lw.learnkotlin.domin

import com.example.lw.learnkotlin.bean.Forecast

data class ForecastList(val city:String,val country:String,val dailyForecast:List<com.example.lw.learnkotlin.domin.model.Forecast>)