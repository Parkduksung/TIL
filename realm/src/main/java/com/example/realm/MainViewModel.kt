package com.example.realm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realm.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import io.realm.kotlin.where
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val realm = Realm.getDefaultInstance()

    private val _getUserLiveData = MutableLiveData<List<User>>()
    val getUserLiveData: LiveData<List<User>> = _getUserLiveData

    private val ceh = CoroutineExceptionHandler { _, _ ->
        Log.d("결과", "Error")
    }

    fun insertUser(user: User) {
        viewModelScope.launch(ceh) {
            realm.executeTransactionAwait(Dispatchers.IO) {
                it.insert(user)
            }
        }
    }

    fun getAllUser() {
        realm.executeTransactionAsync {
            Log.d("결과", it.where<User>().findAll().toList().toString())
        }
    }

}