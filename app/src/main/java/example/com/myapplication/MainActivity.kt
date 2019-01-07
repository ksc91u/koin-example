package example.com.myapplication

import android.os.Bundle
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.examples.helloworld.GreeterGrpc
import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var managedChannel: ManagedChannel
    private lateinit var blockingStub: GreeterGrpc.GreeterBlockingStub

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

        grpc()
    }

    fun grpc() {
        managedChannel = ManagedChannelBuilder.forAddress("artifactory.ksc91u.info", 50051)
            .usePlaintext()
            .build()

        blockingStub = GreeterGrpc.newBlockingStub(managedChannel)

        val request = HelloRequest.newBuilder().setName("johnny").build()
        var response: HelloReply? = null

        try{
            response = blockingStub.sayHello(request)
        }catch (exception : Exception){
            println(">>>> exception $exception")
        }

        println(">>>> response ${response?.message}")

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
