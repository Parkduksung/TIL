package com.example.mqtt

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.connection -> {
                mainViewModel.connectClient { state, message ->
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
                true
            }

            R.id.subscribe -> {
                mainViewModel.subscribe { state, message ->
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
                true
            }

            R.id.publish -> {
                mainViewModel.publish(msg = "Test") { state, message ->
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }
}