package com.example.lw.learnkotlin;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import android.app.Application;

/**
 * Created on 2018/11/23.
 *
 * @author Alan
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
