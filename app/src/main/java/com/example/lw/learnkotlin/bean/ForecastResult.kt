package com.example.lw.learnkotlin.bean

import com.example.lw.learnkotlin.domin.model.Forecast

/**
 * Created on 2018/11/23.
 * @author Alan
 */
data class ForecastResult(val city:City, val list:List<Forecast>)