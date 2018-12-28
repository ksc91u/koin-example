package example.com.myapplication.di

import example.com.myapplication.dto.Version
import kotlinx.coroutines.Deferred
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.http.GET


val networkServiceModule = module {
    single { NetworkServiceRepositoryImpl(get(name = "retrofit")).giveNetworkService() }
}

interface NetworkServiceRepository {
    fun giveNetworkService(): NetworkService
}

class NetworkServiceRepositoryImpl(private val retrofit: Retrofit) : NetworkServiceRepository {

    override fun giveNetworkService(): NetworkService {
        println(">>> giveNetworkService")
        return retrofit.create(NetworkService::class.java)
    }
}

interface NetworkService {
    @GET("/version")
    fun getVersion(): Deferred<Version>
}