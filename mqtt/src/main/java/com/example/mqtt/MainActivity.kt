package com.example.mqtt

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mqtt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    private val mqttAdapter = MqttAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        observationViewState()
    }

    private fun initUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        setContentView(binding.root)

        binding.rvMqttMessage.adapter = mqttAdapter
    }

    private fun observationViewState() {

        mainViewModel.mainViewState.observe(this) { viewState ->

            when (viewState) {

                is MainViewModel.MainViewState.Connection -> {
                    showToast("Connection Success")
                }

                is MainViewModel.MainViewState.Subscribe -> {
                    showToast("Subscribe Success")
                }

                is MainViewModel.MainViewState.Error -> {
                    showToast(viewState.message)
                }

                is MainViewModel.MainViewState.SendMessage -> {
                    mqttAdapter.addMessage(viewState.mqttMessage)
                }

                is MainViewModel.MainViewState.ReceiveMessage -> {
                    mqttAdapter.addMessage(viewState.mqttMessage)
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.connection -> {
                mainViewModel.connectClient()
                true
            }

            R.id.subscribe -> {
                mainViewModel.subscribe()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}