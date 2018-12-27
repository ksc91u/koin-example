package example.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import example.com.myapplication.di.BaseNetworkRepository
import example.com.myapplication.di.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.Lazy
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity() {

    private val networkService: NetworkService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch (IO){
            val v = networkService.getVersion()

            with(Dispatchers.Main) {
                println(">>>> result ${v.await()}")
            }
        }
    }
}
