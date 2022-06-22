package com.example.scrollcapture

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.scrollcapture.MediaUtil.Companion.saveToGallery
import com.example.scrollcapture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        initViewModel()
    }

    private fun initViewModel() {
        binding.viewModel = mainViewModel
        mainViewModel.mainViewState.observe(this) { viewState ->
            when (viewState) {
                is MainViewModel.MainViewState.PermissionResult -> {
                    if (viewState.isGranted) {
                        with(binding) {
                            val totalBitmap = Bitmap.createBitmap(
                                scrollview.getChildAt(0).width,
                                scrollview.getChildAt(0).height,
                                Bitmap.Config.ARGB_8888
                            )
                            val canvas = Canvas(totalBitmap)
                            canvas.drawColor(Color.WHITE)
                            binding.llScroll.draw(canvas)
                            val uri = totalBitmap?.saveToGallery(this@MainActivity)

                            val shareIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_STREAM, uri)
                                type = "image/jpeg"
                            }
                            startActivity(Intent.createChooser(shareIntent, null))
                        }
                    } else {
                        Toast.makeText(this, "Permission Deni", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}