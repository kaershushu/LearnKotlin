package com.example.lw.learnkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.View
import androidx.core.content.ContextCompat
import com.example.lw.learnkotlin.data.db.ForecastDbHelper
import com.example.lw.learnkotlin.domin.ForecastList
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import java.text.DateFormat
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created on 2018/12/3.
 * @author Alan
 */

val View.ctx: Context
    get() = context

//设置NotNull委托，保证在使用的时候不为空，为空的话则抛出异常
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

object CityForecastTable {
    val NAME = "CityForecast"
    val ID = "_id"
    val CITY = "city"
    val COUNTRY = "country"
}

object DayForecastTable {
    val NAME = "DayForecast"
    val ID = "_id"
    val DATE = "date"
    val DESCRIPTION = "description"
    val HIGH = "high"
    val LOW = "low"
    val ICON_URL = "iconUrl"
    val CITY_ID = "cityId"
}

//lambda配合扩展函数
fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> = parseList(object : MapRowParser<T> {
    override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
})

fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? = parseOpt(object : MapRowParser<T> {
    override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
})

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}

fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> = map { Pair(it.key, it.value!!) }.toTypedArray()

inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for (element in this) {
        val result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("No elment matching predicate was found.")
}

fun SelectQueryBuilder.byId(id: Long): SelectQueryBuilder = whereSimple("_id = ?", id.toString())

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}

fun Context.color(res: Int) = ContextCompat.getColor(this, res)

inline fun <reified T:Activity>Context.startActivity(vararg params: Pair<String, String>) {
    val intent = Intent(this, T::class.java)
    params.forEach{
        intent.putExtra(it.first, it.second)
    }
    startActivity(intent)
}
