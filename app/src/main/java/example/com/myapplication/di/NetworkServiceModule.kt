package example.com.myapplication.di

import example.com.myapplication.dto.Version
import kotlinx.coroutines.Deferred
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

val networkServiceModule = module {
    scope(StringQualifier("session")) {
        scoped { NetworkServiceRepositoryImpl(get(StringQualifier("retrofit"))) }
        scoped { get<NetworkServiceRepositoryImpl>().giveNetworkService() }
    }
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

    @GET("/~ksc91u/403.php")
    fun get403():Deferred<Response<Unit>>

    @GET("/~ksc91u/403.php")
    fun get403a():Deferred<String>
}
