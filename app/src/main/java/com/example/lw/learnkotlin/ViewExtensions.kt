package com.example.lw.learnkotlin

import android.content.Context
import android.view.View
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created on 2018/12/3.
 * @author Alan
 */

val View.ctx: Context
    get() = context

//设置NotNull委托，保证在使用的时候不为空，为空的话则跑出异常
private class NotNullSingleValueVar<T : Any> : ReadWriteProperty<Any?, T> {
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("Property ${property.name} should be initialized before get.")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}

object DelegatesExt {
    fun <T : Any> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
}

//数据库里各个元素的object

object CityForecastTable{
    val NAME = "CityForecast"
    val ID = "_id"
    val CITY = "city"
    val COUNTRY = "country"
}

object DayForecastTable{
    val NAME = "DayForecast"
    val ID = "_id"
    val DATE = "date"
    val DESCRIPTION = "description"
    val HIGH = "high"
    val LOW = "low"
    val ICON_URL = "iconUrl"
    val CITY_ID = "cityId"
}