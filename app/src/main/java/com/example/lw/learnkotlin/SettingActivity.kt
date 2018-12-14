package com.example.lw.learnkotlin

import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class SettingActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar
        get() = find(R.id.toolbar)

    companion object {
        const val ZIP_CODE = "SettingActivity:zipcode"
        const val DEFAULT_ZIP = 94043L
    }

    private var zipCode: Long by DelegatesExt.longPreference(this, SettingActivity.ZIP_CODE, SettingActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        et_city_code.text = Editable.Factory.getInstance().newEditable("$zipCode")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed(); true
        }
        else -> false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        try {
            zipCode = et_city_code.text.toString().toLong()
        }catch (e : NumberFormatException){
            App.instance.toast("老铁，这个不能转成Long")
        }
    }
}
