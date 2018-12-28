package example.com.myapplication

import android.os.Bundle
import example.com.myapplication.di.NetworkService
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val networkService: NetworkService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            val version = networkService.getVersion().await()
            log(">>>> result $version")
        }

    }
}
