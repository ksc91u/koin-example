package example.com.myapplication

import android.app.Application
import example.com.myapplication.di.networkServiceModule
import example.com.myapplication.di.retrofitModule
import org.koin.android.ext.android.startKoin

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(retrofitModule, networkServiceModule))
    }
}
