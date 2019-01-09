package example.com.myapplication

import android.app.Application
import example.com.myapplication.di.networkServiceModule
import example.com.myapplication.di.retrofitModule
import example.com.myapplication.di.viewModelModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.startKoin
import org.koin.core.scope.Scope

class KoinApplication : Application() {

    lateinit var scope: Scope
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(retrofitModule, networkServiceModule, viewModelModule))

        scope = getKoin().getOrCreateScope("session")
    }

    fun refreshScope() {
        scope.close()
        scope = getKoin().getOrCreateScope("session")
    }
}
