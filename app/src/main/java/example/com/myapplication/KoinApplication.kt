package example.com.myapplication

import android.app.Application
import example.com.myapplication.di.networkServiceModule
import example.com.myapplication.di.retrofitModule
import example.com.myapplication.di.viewModelModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.StringQualifier
import org.koin.core.scope.Scope
import org.koin.dsl.koinApplication
import java.util.*

class KoinApplication : Application() {

    lateinit var scope: Scope
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@KoinApplication)
            modules(retrofitModule, networkServiceModule, viewModelModule)
        }
        scope = getKoin().createScope("scope id", StringQualifier("session") )
    }

    fun refreshScope() {
        scope.close()
        scope = getKoin().createScope("scope id " + Random().nextInt() , StringQualifier("session") )
    }
}
