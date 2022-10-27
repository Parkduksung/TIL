package com.example.references

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //weak reference 는 gc 수거시 제거대상이 되어 없어짐.
        run {
            var weakEmployee: Employee? by weakReference(null)

            weakEmployee = Employee("duksung-weak", "1qwevesa")

            Log.d("결과-WeakReference", weakEmployee.toString())

            Runtime.getRuntime().gc()

            Log.d("결과-WeakReference", weakEmployee.toString())
        }

        //soft reference 는 메모리 사용 한계가 초과되었을 경우 제거 대상에 추가되어 없어짐.
        run {
            var softEmployee: Employee? by softReference(null)

            softEmployee = Employee("duksung-soft", "fevq24d")

            Log.d("결과-SoftReference", softEmployee.toString())

            Runtime.getRuntime().gc()

            Log.d("결과-SoftReference", softEmployee.toString())

        }

        //strong reference 로 만들어진 객채를 weak reference 가 사용했을경우 gc 수거했을때 수거되어지지 않는다..
        run {
            val strongEmployee = Employee("duksung-strong", "1qwevesa")

            var weakEmployee: Employee? by weakReference(strongEmployee)

            Log.d("결과-WeakReference in StringEmployee", weakEmployee.toString())

            Runtime.getRuntime().gc()

            Log.d("결과-WeakReference in StringEmployee", weakEmployee.toString())
        }
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


fun <T> softReference(tIn: T? = null): ReadWriteProperty<Any?, T?> {

    return object : ReadWriteProperty<Any?, T?> {
        var t = SoftReference<T?>(tIn)

        override fun getValue(thisRef: Any?, property: KProperty<*>): T? =
            t.get()

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            t = SoftReference(value)
        }
    }
}