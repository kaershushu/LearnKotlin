package com.example.lw.learnkotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.lw.learnkotlin.domin.model.Forecast
import com.example.lw.learnkotlin.request.RequestDayForecastCommand
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import org.jetbrains.anko.uiThread
import java.text.DateFormat

class DetailActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    companion object {
        const val CITY_NAME = "city_name"
        const val CITY_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val title = intent.getStringExtra(CITY_NAME)
        val id = intent.getLongExtra(CITY_ID, 0)
        doAsync {
            val result = bg{RequestDayForecastCommand(id).execute()}
            with(RequestDayForecastCommand(id).execute()) {
                uiThread {

                }
            }
            bindForecast(result.await())
        }
        initToolbar()
        toolbarTitle = title
        enableHomeUp { onBackPressed() }
    }

    private fun bindForecast(forecast: Forecast) {
        supportActionBar!!.subtitle = forecast.date.toDateString(DateFormat.FULL)
        bindWeather(forecast.high to maxTemperature, forecast.low to minTemperature)
        tvweatherdescription.text = forecast.description
        Picasso.get().load(forecast.iconUrl).into(icon)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}
