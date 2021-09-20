package com.example.til.url

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGImageView
import com.caverock.androidsvg.SVGParser
import com.example.til.R
import kotlinx.coroutines.MainScope
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

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
                imageView.setImageDrawable(pictureDrawable)
            }
        }.start()
    }


}