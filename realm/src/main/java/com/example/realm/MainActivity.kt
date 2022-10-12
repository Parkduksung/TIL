package com.example.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.where

class MainActivity : AppCompatActivity() {

    private val realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val user1 = User(
//            id = 2,
//            name = "박덕",
//            age = 31
//        )
//        insert(user1)

        getAllUser()?.let {
            Log.d("결과", it.toString())
        } ?: Log.d("결과", "못가져옴")

    }

    private fun insert(user: User) {
        realm.executeTransactionAsync {
            it.insertOrUpdate(user)
        }
    }

    // 모든 사용자 읽어오기
    private fun getAllUser(): RealmResults<User> {
        return realm.where<User>()
            .findAll()
            .sort("id", Sort.ASCENDING)
    }

    // 특정 사용자 읽어오기
    private fun getUser(id: Int): User? = realm.where<User>()
        .equalTo("id", id)
        .findFirst()

    // 모든 사용자 삭제
    fun deleteAllUser() {
        realm.executeTransaction {
            it.where<User>().findAll().deleteAllFromRealm()
        }
    }

    // 특정 사용자 삭제
    fun deleteUser(id: Int) {
        realm.executeTransaction {
            it.where<User>().equalTo("id", id).findAll().deleteAllFromRealm()
        }
    }
}