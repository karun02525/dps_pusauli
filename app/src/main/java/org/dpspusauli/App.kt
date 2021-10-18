package org.dpspusauli

import android.app.Application
import android.content.Context
import org.dpspusauli.di.networkModule
import org.dpspusauli.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val context = this
        appContext = applicationContext

        val moduleList=listOf(networkModule, viewModelModule)
        startKoin { modules(moduleList).androidContext(context) }
    }

    companion object {
        @get:Synchronized
        var appContext: Context? = null
            private set
    }

}