package com.example.lw.learnkotlin

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.find
import android.widget.Toast
import com.example.lw.learnkotlin.bean.Forecast
import com.example.lw.learnkotlin.request.LocalStrategy
import com.example.lw.learnkotlin.request.RequestImpl
import com.orhanobut.logger.Logger
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mItems: ArrayList<Forecast> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // as 用于转型
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val button = find<Button>(R.id.btn)

        button.setOnClickListener {
            doAsyncResult{
                val json = RequestImpl().request(LocalStrategy())
                Logger.d(json)
                uiThread {
                    toast("request performed")
                }
            }
        }

        recycler.layoutManager = LinearLayoutManager(this)

        val p1 = Forecast(Date(), 22.0f, "sunny")
        val p2 = Forecast(Date(), 16f, "rainy")
        val p3 = Forecast(Date(), 23f, "sunny")

        mItems.add(p1)
        mItems.add(p2)
        mItems.add(p3)

        val adapter = ForecastListAdapter(mItems)

        recycler.adapter = adapter

    }

    private fun Context.toast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}
