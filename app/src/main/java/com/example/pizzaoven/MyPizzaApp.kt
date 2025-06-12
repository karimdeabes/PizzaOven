package com.example.pizzaoven

import android.app.Application
import com.example.pizzaoven.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyPizzaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyPizzaApp)
            androidLogger()
            modules(
                appModule,
            )
        }
    }
}