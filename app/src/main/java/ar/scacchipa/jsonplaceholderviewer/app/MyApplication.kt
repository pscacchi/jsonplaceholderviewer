package ar.scacchipa.jsonplaceholderviewer.app

import android.app.Application
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}