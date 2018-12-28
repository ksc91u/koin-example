package example.com.myapplication

import android.app.Application
import example.com.myapplication.di.*
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.Retrofit

class KoinApplication : Application() {

    val okhttpModule = module {
        single<OkHttpClient> {BaseNetworkRepositoryImpl().giveOkHttp()}
    }

    val retrofitModule = module {
        // single instance of HelloRepository
        single<Retrofit>(name="retrofit") { BaseNetworkRepositoryImpl().giveRetrofit(get()) }
    }

    val networkServiceModule = module {
        single<NetworkService> {NetworkServiceRepositoryImpl(get()).giveNetworkService()}
    }


    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(okhttpModule, retrofitModule, networkServiceModule))
    }
}
