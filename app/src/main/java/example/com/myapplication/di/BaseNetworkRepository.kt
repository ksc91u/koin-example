package example.com.myapplication.di

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import kotlinx.android.parcel.Parcelize

interface BaseNetworkRepository {
    fun giveOkHttp(): OkHttpClient
    fun giveRetrofit(): Retrofit
}

class BaseNetworkRepositoryImpl() : BaseNetworkRepository {

    override fun giveRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo5730615.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    override fun giveOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

}

interface NetworkServiceRepository {
    fun giveNetworkService(): NetworkService
}

class NetworkServiceRepositoryImpl(val base : BaseNetworkRepository) : NetworkServiceRepository {
    override fun giveNetworkService(): NetworkService {
        return base.giveRetrofit().create(NetworkService::class.java)
    }
}

@Parcelize
data class Version(
    @SerializedName("msg") val msg: String
) : Parcelable

interface NetworkService {
    @GET("/version")
    fun getVersion(): Deferred<Version>
}