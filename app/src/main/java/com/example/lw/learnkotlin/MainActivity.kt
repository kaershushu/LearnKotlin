package com.example.lw.learnkotlin

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lw.learnkotlin.request.ForecastProvider
import com.example.lw.learnkotlin.request.ForecastRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    val zipCode: Long by DelegatesExt.longPreference(this, SettingActivity.ZIP_CODE, SettingActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // as 用于转型
        val recycler = findViewById<RecyclerView>(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this@MainActivity)

        doAsyncResult {
            val result = ForecastRequest(zipCode, ForecastProvider()).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.CITY_ID to it.id, DetailActivity.CITY_NAME to result.city)
                }
                recycler.adapter = adapter

                initToolbar()
                toolbarTitle = "${result.city} (${result.city})"
                attachToScroll(recycler)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_settings -> toolbar.ctx.startActivity<SettingActivity>()
            else -> App.instance.toast("Unknown option")
        }
        return super.onOptionsItemSelected(item)
    }

}
