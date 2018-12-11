package com.example.lw.learnkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lw.learnkotlin.request.ForecastProvider
import com.example.lw.learnkotlin.request.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // as 用于转型
        val recycler = findViewById<RecyclerView>(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this@MainActivity)

        doAsyncResult {
            val result = RequestForecastCommand(94043, ForecastProvider()).execute()
            val adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.CITY_ID to result.id, DetailActivity.CITY_NAME to result.city )
            }
            recycler.adapter = adapter
        }
    }
}
