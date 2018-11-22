package com.example.lw.learnkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val items = listOf("1","2","3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // as 用于转型
        val recycler = findViewById(R.id.recycler) as RecyclerView

        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = ForecastListAdapter(items)

        recycler.adapter = adapter

    }
}
