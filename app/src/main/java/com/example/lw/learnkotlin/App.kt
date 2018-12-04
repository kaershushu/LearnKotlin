package com.example.lw.learnkotlin

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

import android.app.Application

/**
 * Created on 2018/11/23.
 *
 * @author Alan
 */
class App : Application() {

    companion object {
        private var instance:Application ?= null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
