package com.example.lw.learnkotlin.data.db

import android.database.sqlite.SQLiteDatabase
import com.example.lw.learnkotlin.*
import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.CityForecast
import com.example.lw.learnkotlin.domin.model.DayForecast
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

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
        }
    }

    fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }


}