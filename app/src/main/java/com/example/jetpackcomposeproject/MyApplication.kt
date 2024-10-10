package com.example.jetpackcomposeproject

import android.app.Application

class MyApplication :Application() {
    companion object {
        var instance = MyApplication()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}