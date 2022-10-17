package com.example.checkbox

import android.widget.RadioGroup
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    var checkAgree = CheckAgree()

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> get() = _mainViewStateLiveData


    var check1ObservableField = ObservableField(false)
        set(value) {
            field = value
            checkbox()
        }

    var check2ObservableField = ObservableField(false)
        set(value) {
            field = value
            checkbox()
        }
    var check3ObservableField = ObservableField(false)
        set(value) {
            field = value
            checkbox()
        }

    fun setCheckedRadio(radioGroup: RadioGroup, index: Int) {
        when (index) {
            1 -> {
                checkAgree.agree1 = true
                checkAgree.agree2 = true
                checkAgree.agree3 = true

                _mainViewStateLiveData.value = MainViewState.ActivateRadio(true)
            }
            3 -> {
                checkAgree.agree1 = false
                checkAgree.agree2 = false
                checkAgree.agree3 = false
                _mainViewStateLiveData.value = MainViewState.ActivateRadio(true)
            }
            else -> {
                if (!check1ObservableField.get()!! &&
                    !check2ObservableField.get()!! &&
                    !check3ObservableField.get()!!
                ) {
                    _mainViewStateLiveData.value = MainViewState.ClearRadio
                }
            }
        }
    }


    fun play() {

    }

    fun reward() {

    }


    private fun checkbox() {


        if (!check1ObservableField.get()!! &&
            !check2ObservableField.get()!! &&
            !check3ObservableField.get()!!
        ) {
            _mainViewStateLiveData.value = MainViewState.ClearRadio
        }
    }
}

class CheckAgree : BaseObservable() {

    @get:Bindable
    var agree1: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.agree1)
        }

    @get:Bindable
    var agree2: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.agree2)
        }

    @get:Bindable
    var agree3: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.agree3)
        }

}
