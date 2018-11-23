package com.example.lw.learnkotlin.bean

/**
 * Created on 2018/11/23.
 * @author Alan
 */
data class Forecast(val dt: Long, val temperature: Temperature, val pressure: Float, val humanity: Int, val weather: List<Weather>, val speed: Float, val deg: Int, val clouds: Int, val rain: Float)