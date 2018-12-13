package com.example.lw.learnkotlin

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.find

class SettingActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar
        get() = find(R.id.toolbar)

    companion object {
        const val ZIP_CODE = "SettingActivity:zipcode"
        const val DEFAULT_ZIP = 94043L
    }

    var zipCode: Long by DelegatesExt.longPreference(this, SettingActivity.ZIP_CODE, SettingActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed(); true
        }
        else -> false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        zipCode = et_city_code.text.toString().toLong()
    }
}
