package com.example.lw.learnkotlin

import com.example.lw.learnkotlin.domin.ForecastList
import com.example.lw.learnkotlin.domin.model.Forecast
import com.example.lw.learnkotlin.request.ForecastDataSource
import com.example.lw.learnkotlin.request.ForecastProvider
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created on 2018/12/14.
 * @author Alan
 */
class ForecastProviderTest {
    @Test
    fun testDateSource() {
        val ds = mock(ForecastDataSource::class.java)
        `when`(ds.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }

        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestForecast(0))
    }

    @Test fun emptyDatabaseReturnsServerValue(){
        val db = mock(ForecastDataSource::class.java)
        val server = mock(ForecastDataSource::class.java)
        `when`(server.requestForecastByZipCode(any(Long::class.java), any(Long::class.java))).then { ForecastList(0,"city", "country", listOf()) }
        val provider = ForecastProvider(listOf(db,server))
        assertNotNull(provider.requestByZipCode(0,0))
    }

}