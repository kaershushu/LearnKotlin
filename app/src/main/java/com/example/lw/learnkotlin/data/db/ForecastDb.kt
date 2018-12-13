package com.example.lw.learnkotlin.data.db

import android.database.sqlite.SQLiteDatabase
import com.example.lw.learnkotlin.*
import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.CityForecast
import com.example.lw.learnkotlin.domin.model.DayForecast
import com.example.lw.learnkotlin.domin.model.Forecast
import com.example.lw.learnkotlin.request.ForecastDataSource
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created on 2018/12/5.
 * @author Alan
 */
class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 private val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {


    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? " + "AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        city?.let {
            dataMapper.convertToDomain(it)
        }
    }

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use{
        val forecast = select(DayForecastTable.NAME).byId(id).parseOpt{
            DayForecast(HashMap(it))
        }
        if (forecast != null) dataMapper.convertDayToDomain(forecast) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }

    fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }


}