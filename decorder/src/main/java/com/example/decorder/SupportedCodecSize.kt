package com.example.decorder

import android.media.MediaCodec
import android.util.Size
import androidx.annotation.RequiresApi
import kotlin.math.absoluteValue

class SupportedCodecSize constructor(
    private val mediaCodec: MediaCodec,
    private val mime: String,
    private var resolution: Size
) {

    var width = resolution.width
    var height = resolution.height

    init {
        val supportedSize = getSupportedVideoSize()
        width = supportedSize.width
        height = supportedSize.height
    }

    @RequiresApi(21)
    private fun getSupportedVideoSize(): Size {
        // 주어진 Size가 기기에서 지원하는지 먼저 체크한다.
        if (isResolutionSupported(resolution)) {
            return resolution
        }

        val resolutions = mutableListOf(
            Size(176, 144),
            Size(320, 240),
            Size(320, 180),
            Size(640, 360),
            Size(720, 480),
            Size(1280, 720),
            Size(1920, 1080),
            Size(1920, 1080)
        )

        // 화소수
        val pixel: Int = resolution.width * resolution.height
        val preferredAspect: Float = resolution.width.toFloat() / resolution.height.toFloat()

        // 비율에 맞는 최대한 가까운 크기로 정렬한다.
        val nearestToFurthest = resolutions.sortedWith(
            compareBy(
                { pixel - it.width * it.height },
                {
                    val aspect =
                        if (it.width < it.height) it.width.toFloat() / it.height.toFloat() else it.height.toFloat() / it.width.toFloat()
                    (preferredAspect - aspect).absoluteValue
                })
        )

        // 가까운 크기부터 순차적으로 체크하고 지원이 되면 해당 Size를 return 한다.
        val result = nearestToFurthest.firstOrNull {
            isResolutionSupported(it)
        }

        if (result != null) {
            return result
        }

        throw RuntimeException("Couldn't find supported resolution")
    }

    @RequiresApi(21)
    private fun isResolutionSupported(size: Size): Boolean {
        return mediaCodec.codecInfo.getCapabilitiesForType(mime).videoCapabilities.isSizeSupported(
            size.width,
            size.height
        )
    }

}