package com.gemslight.themoviedb

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TheMovieDb : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { thread,throweble ->
            Log.e(
                "Global Error",
                throweble.message, throweble.cause
            )
        }
    }
}