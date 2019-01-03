package example.com.myapplication

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = get()

        btn.setOnClickListener {
            this@MainActivity.refresh()
        }

        switchBtn.setOnClickListener {
            (application as KoinApplication).refreshScope()
            this@MainActivity.refresh()
        }
    }

    fun refresh() {
        mainViewModel = get()
        launch {
            val version = mainViewModel.getVersion()
            textView.text = version.msg
            log(">>>> result $version")
        }
    }
}
