package com.superyao.taipeizoo

import android.app.Application
import com.superyao.taipeizoo.log.DebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            System.setProperty("kotlinx.coroutines.debug", "on")
            Timber.plant(DebugTree("DEV541"))
        }
    }
}