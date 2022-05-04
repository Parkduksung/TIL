package com.example.scrollcapture

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(private val app: Application) : AndroidViewModel(app) {

    private val _mainViewState = MutableLiveData<MainViewState>()
    val mainViewState: LiveData<MainViewState> = _mainViewState


    private val context
        get() = app.applicationContext

    fun capture() {
        context.checkPermission { onChangedViewState(MainViewState.PermissionResult(it)) }
    }

    private fun onChangedViewState(viewState: MainViewState) {
        _mainViewState.value = viewState
    }

    sealed class MainViewState {
        data class PermissionResult(val isGranted: Boolean) : MainViewState()
    }
}