package example.com.myapplication

import android.arch.lifecycle.ViewModel
import example.com.myapplication.di.NetworkService
import example.com.myapplication.di.NetworkServiceRepositoryImpl
import kotlinx.coroutines.coroutineScope

class MainViewModel(
    private val networkService: NetworkService,
    private val repo: NetworkServiceRepositoryImpl
) : ViewModel() {

    suspend fun getVersion() = coroutineScope {
        //main
        networkService.getVersion(repo.name).await()
    }

    protected fun finalize() {
        println(">>>> MainViewModel gc")
    }
}
