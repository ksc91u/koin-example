package example.com.myapplication

import android.app.Application
import example.com.myapplication.di.*
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.Retrofit

class KoinApplication : Application() {

    val appModule = module {

        // single instance of HelloRepository
        single<BaseNetworkRepository>(name="baseNetworkRepository") { BaseNetworkRepositoryImpl() }
        single<NetworkService> {NetworkServiceRepositoryImpl(get()).giveNetworkService()}

    }


    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}
