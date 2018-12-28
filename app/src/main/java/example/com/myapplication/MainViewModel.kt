package example.com.myapplication

import android.arch.lifecycle.ViewModel
import example.com.myapplication.di.NetworkService
import kotlinx.coroutines.coroutineScope

class MainViewModel(private val networkService: NetworkService) : ViewModel() {

    suspend fun getVersion() = coroutineScope {
        //main
        networkService.getVersion().await()
    }

    protected fun finalize() {
        println(">>>> MainViewModel gc")
    }
}