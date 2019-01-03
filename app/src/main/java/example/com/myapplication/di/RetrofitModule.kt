package example.com.myapplication.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val retrofitModule = module {
    single { RetrofitRepositoryImpl() }
    single { get<RetrofitRepositoryImpl>().giveOkHttp() }
    single(name = "retrofit") { get<RetrofitRepositoryImpl>().giveRetrofit(get()) }
}

interface RetrofitRepository {

    fun giveOkHttp(): OkHttpClient

    fun giveRetrofit(okHttpClient: OkHttpClient): Retrofit

}

class RetrofitRepositoryImpl : RetrofitRepository {

    override fun giveRetrofit(okHttpClient: OkHttpClient): Retrofit {
        println(">>> giveRetrofit")
        return Retrofit.Builder()
            .baseUrl("https://www2.cs.ccu.edu.tw/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    override fun giveOkHttp(): OkHttpClient {
        println(">>> giveOkHttp")
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

}