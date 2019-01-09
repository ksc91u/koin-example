package example.com.myapplication.di

import example.com.myapplication.dto.Version
import kotlinx.coroutines.Deferred
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

val networkServiceModule = module {
    scope("session") { NetworkServiceRepositoryImpl(get(name = "retrofit")) }
    scope("session") { get<NetworkServiceRepositoryImpl>().giveNetworkService() }
}

interface NetworkServiceRepository {
    fun giveNetworkService(): NetworkService
}

class NetworkServiceRepositoryImpl(private val retrofit: Retrofit) : NetworkServiceRepository {

    val name: String by lazy {
        return@lazy listOf<String>("lisa", "rose", "john", "mars", "nick", "andy", "jean", "lee").shuffled().first()
    }

    override fun giveNetworkService(): NetworkService {
        println(">>> giveNetworkService")
        return retrofit.create(NetworkService::class.java)
    }
}

interface NetworkService {
    @GET("/~ksc91u/mock.php")
    fun getVersion(@Query("link") name: String): Deferred<Version>
}
