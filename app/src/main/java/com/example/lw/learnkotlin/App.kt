package com.example.lw.learnkotlin

import android.app.Application
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * Created on 2018/11/23.
 *
 * @author Alan
 */
class App : Application() {

    companion object {
        var instance: Application by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(instance)
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
