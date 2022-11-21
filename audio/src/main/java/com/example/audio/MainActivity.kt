package com.example.audio

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val musicList = mutableListOf<MusicListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPermission()

        scanFiles()
    }


    // 권한 받아오기
    private fun getPermission() {
        val checkPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            setMusicList()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                99
            )
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 99) {
            setMusicList()
        }
    }

    // 새로 추가된 파일 scan
    private fun scanFiles() {
        val path = arrayOf("/storage/")
        MediaScannerConnection.scanFile(
            applicationContext,
            path,
            null
        ) { path, _ ->
            Log.i("onScanCompleted", "$path is scanned")
        }
    }


    private fun setMusicList() {
        val listUrl = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )

        val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"

        contentResolver.query(
            listUrl,
            proj,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getString(0)
                val title = cursor.getString(1)
                val duration = cursor.getLong(4)

                if (duration <= (10 * 1000)) {
                    val music = MusicListItem(id, title)
                    musicList.add(music)
                }

            }
        }
    }

    private fun playMusic(url: Uri) {
        try {
            val musicPlayer = MediaPlayer.create(this, url)

            musicPlayer.run {
                reset()
                setDataSource(this@MainActivity, url)
                prepare()
            }

            musicPlayer.start()
        } catch (e: Exception) {
            Log.d("결과", e.toString())
        }
    }

}

data class MusicListItem(
    val id: String,
    val title: String
) {
    fun getMusicUri(): Uri {
        return Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
    }
}
