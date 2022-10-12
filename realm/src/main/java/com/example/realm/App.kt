package com.example.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config: RealmConfiguration = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .name("user.realm") // 생성할 realm db 이름
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)
    }
}