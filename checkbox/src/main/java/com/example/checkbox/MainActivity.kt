package com.example.checkbox

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.checkbox.databinding.ActivityMainBinding

/**
 * 유선 면접 테스트의 구현을 진행할 기본 액티비티 입니다.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by lazy {
        ViewModelProvider(this@MainActivity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    return MainViewModel() as T
                } else throw  IllegalArgumentException()
            }
        })[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModel()
    }

    private fun initUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        with(binding) {
            textLogger.movementMethod = ScrollingMovementMethod()
        }

        binding.lifecycleOwner = this

    }

    private fun initViewModel() {

        binding.viewModel = mainViewModel

        binding.termsStatusRadioGroup.setOnCheckedChangeListener(mainViewModel::setCheckedRadio)

        mainViewModel.mainViewStateLiveData.observe(this) { viewState ->
            onChangedMainViewState(viewState)
        }


    }

    private fun onChangedMainViewState(viewState: MainViewState) {
        when (viewState) {

            is MainViewState.ActivateRadio -> {
//                binding.termsStatusRadioGroup.isActivated = viewState.isActive
            }

            is MainViewState.ClearRadio -> {
                binding.termsStatusRadioGroup.clearCheck()
            }

        }
    }


}
