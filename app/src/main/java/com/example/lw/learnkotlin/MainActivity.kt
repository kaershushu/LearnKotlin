package com.example.lw.learnkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val items: ArrayList<Person> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // as 用于转型
        val recycler = findViewById(R.id.recycler) as RecyclerView

        recycler.layoutManager = LinearLayoutManager(this)

        val p1 = Person()
        val p2 = Person()
        val p3 = Person()

        p1.name = "elsa"
        p2.name = "eva"
        p3.name = "tina"


        items.add(p1)
        items.add(p2)
        items.add(p3)

        val adapter = ForecastListAdapter(items)

        recycler.adapter = adapter

    }
}
