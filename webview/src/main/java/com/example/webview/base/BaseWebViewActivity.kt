package com.example.webview.base

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseWebViewActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: B

    protected abstract val bindLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, bindLayout)
        binding.lifecycleOwner =this
        setContentView(binding.root)

        initUi()
    }

    abstract fun initUi()

    protected open fun initWebView(
        webView: WebView,
        progressBar: ProgressBar,
        block: WebView.() -> Unit
    ) {
        with(webView) {
            settings.javaScriptEnabled = true
            block()

            webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progressBar.bringToFront()
                    progressBar.isVisible = true
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
//                    Log.d("shouldOverrideUrlLoading url ${request.url}")
                    view.loadUrl(request.url.toString())
                    return true
                }

                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    progressBar.isVisible = false
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, newProgress: Int) {
                    progressBar.progress = newProgress
                }
            }
        }
    }
}
