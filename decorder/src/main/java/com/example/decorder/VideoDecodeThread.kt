package com.example.decorder

import android.content.res.AssetFileDescriptor
import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaFormat
import android.os.Build
import android.view.Surface
import androidx.annotation.RequiresApi
import java.nio.ByteBuffer

class VideoDecodeThread : Thread() {

    companion object {
        private const val VIDEO = "video/avc"
    }

    private lateinit var extractor: MediaExtractor
    private lateinit var decoder: MediaCodec

    private var isStop = false

    @RequiresApi(Build.VERSION_CODES.N)
    fun init(surface: Surface, file: AssetFileDescriptor): Boolean {
        isStop = false
        try {
            extractor = MediaExtractor().apply {
                setDataSource(file)
            }

            IntRange(0, extractor.trackCount).forEach { index ->

                val format = extractor.getTrackFormat(index)

                val mime = format.getString(MediaFormat.KEY_MIME)

                if(mime?.startsWith(VIDEO) == true){
                    extractor.selectTrack(index)
                    decoder = MediaCodec.createDecoderByType(mime)

                    try {
                        decoder.configure(format,surface,null,0)
                    }catch (e : IllegalStateException){
                        return false
                    }

                    decoder.start()
                    return true
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    override fun run() {
        val newBufferInfo = MediaCodec.BufferInfo()
        val inputBuffers : Array<ByteBuffer> = decoder.inputBuffers
        decoder.outputBuffers

        var isInput = true
        var isFirst = false
        var startWhen = 0L

        while (isStop.not()){
            decoder.dequeueInputBuffer(1000).takeIf { it >= 0 }?.let { index ->
                // fill inputBuffers[inputBufferIndex] with valid data
                val inputBuffer = inputBuffers[index]

                val sampleSize = extractor.readSampleData(inputBuffer, 0)

                if (extractor.advance() && sampleSize > 0) {
                    decoder.queueInputBuffer(index, 0, sampleSize, extractor.sampleTime, 0)
                } else {
                    decoder.queueInputBuffer(index, 0, 0, 0, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
                    isInput = false
                }
            }

            val outIndex = decoder.dequeueOutputBuffer(newBufferInfo, 1000)

            when (outIndex) {
                MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED -> {
                    decoder.outputBuffers
                }
                MediaCodec.INFO_OUTPUT_FORMAT_CHANGED -> {}
                MediaCodec.INFO_TRY_AGAIN_LATER -> {}
                else -> {
                    if (isFirst.not()) {
                        startWhen = System.currentTimeMillis()
                        isFirst = true
                    }
                    try {
                        val sleepTime: Long = newBufferInfo.presentationTimeUs / 1000 - (System.currentTimeMillis() - startWhen)
                        if (sleepTime > 0) sleep(sleepTime)

                    } catch (e: InterruptedException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
                    }
                    decoder.releaseOutputBuffer(outIndex, true)
                }
            }

            if (newBufferInfo.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0) {
                break
            }
        }

        decoder.stop()
        decoder.release()
        extractor.release()
    }

    fun close() {
        isStop = true
    }
}