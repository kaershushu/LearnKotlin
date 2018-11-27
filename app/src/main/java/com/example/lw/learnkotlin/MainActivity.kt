package com.example.lw.learnkotlin

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lw.learnkotlin.bean.Forecast
import com.example.lw.learnkotlin.bean.ForecastResult
import com.example.lw.learnkotlin.domin.ForecastDataMapper
import com.example.lw.learnkotlin.request.OpenWeatherMapStrategy
import com.example.lw.learnkotlin.request.RequestImpl
import com.orhanobut.logger.Logger
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // as 用于转型
        val recycler = findViewById<RecyclerView>(R.id.recycler)

        doAsyncResult {
            val json = RequestImpl().execute(OpenWeatherMapStrategy("94043"))
            val forecastResult = ForecastDataMapper().convertFromDataModel(json)

            uiThread {
                recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                recycler.adapter = ForecastListAdapter(forecastResult)
            }
            Logger.d(json)
            uiThread {
                toast("request performed")
            }
        }
    }

    private fun Context.toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}
