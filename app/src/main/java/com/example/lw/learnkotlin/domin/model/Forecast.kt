package com.example.lw.learnkotlin.domin.model

data class Forecast(val id: Long, val date: String, val description: String, val high: Int, val low: Int, val iconUrl: String)