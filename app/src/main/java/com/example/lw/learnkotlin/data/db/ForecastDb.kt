package com.example.lw.learnkotlin.data.db

import com.example.lw.learnkotlin.CityForecastTable
import com.example.lw.learnkotlin.DayForecastTable
import com.example.lw.learnkotlin.domin.model.CityForecast
import com.example.lw.learnkotlin.domin.model.DayForecast
import com.example.lw.learnkotlin.parseList
import com.example.lw.learnkotlin.parseOpt
import org.jetbrains.anko.db.select

/**
 * Created on 2018/12/5.
 * @author Alan
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) {


    fun requestForecastByZipcode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? " + "AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME).whereArgs(dailyRequest, "id" to zipCode, "date" to date)
                .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME).whereSimple("${CityForecastTable.ID} = ? ", zipCode.toString()).parseOpt {
            CityForecast(HashMap(it), dailyForecast)
        }
        city
    }
}