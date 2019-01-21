package example.com.myapplication

import android.app.Application
import example.com.myapplication.di.networkServiceModule
import example.com.myapplication.di.retrofitModule
import example.com.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@KoinApplication)
            modules(retrofitModule, networkServiceModule, viewModelModule)
        }
    }

    fun refreshScope() {
    }
}
