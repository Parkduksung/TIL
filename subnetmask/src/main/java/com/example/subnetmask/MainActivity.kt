package com.example.subnetmask

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.subnetmask.ui.theme.TILTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.InetAddress
import java.net.NetworkInterface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSubnetMask()
        setContent {
            TILTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun getSubnetMask() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val ip = InetAddress.getLocalHost()

                // Get the default network interface
                val network = NetworkInterface.getByInetAddress(ip)

                // Get the subnet mask
                val interfaceAddress = network.interfaceAddresses[0]
                val subnetMask = interfaceAddress.networkPrefixLength

                // Print the subnet mask
                Log.d("결과",subnetMask.toString())
            }catch (e: Exception){
                Log.d("결과",e.toString())
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TILTheme {
        Greeting("Android")
    }
}
