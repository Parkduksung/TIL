package com.example.til.url

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.caverock.androidsvg.SVG
import com.example.til.R
import java.net.URL

class UrlSGVActivity : AppCompatActivity(R.layout.activity_url_svg) {

    private val imageView: ImageView by lazy { findViewById(R.id.iv_svg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Thread {
            val url = URL("https://www.metaweather.com/static/img/weather/s.svg")

            val urlConnection = url.openConnection()

            val svg: SVG = SVG.getFromInputStream(urlConnection.getInputStream())

            val picture = svg.renderToPicture()

            val pictureDrawable = PictureDrawable(picture)

            runOnUiThread {
                Glide.with(this).load(pictureDrawable.current).into(imageView)
            }
        }.start()
    }


}