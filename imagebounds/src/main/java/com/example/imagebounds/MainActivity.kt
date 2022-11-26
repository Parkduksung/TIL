package com.example.imagebounds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.imagebounds.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide.with(this)
            .load(MOCK_URL)
            .into(binding.image)

    }


    companion object {
        private const val MOCK_URL = "https://picsum.photos/1000/700?image=180"
    }
}