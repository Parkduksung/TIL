package com.example.references

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking
import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var weakEmployee: Employee? by weakReference(null)

        weakEmployee = Employee("duksung","1qwevesa")

        Log.d("결과", weakEmployee.toString())

        Runtime.getRuntime().gc()

        Log.d("결과", weakEmployee.toString())

    }
}


data class Employee(val name: String, val id: String)


fun <T> weakReference(tIn: T? = null): ReadWriteProperty<Any?, T?> {

    return object : ReadWriteProperty<Any?, T?> {
        var t = WeakReference<T?>(tIn)

        override fun getValue(thisRef: Any?, property: KProperty<*>): T? =
            t.get()

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            t = WeakReference(value)
        }
    }
}