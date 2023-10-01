package com.demo.common_helper

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class AppWebViewClients(private val progressBar: ProgressBar

) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        progressBar.visibility = View.GONE
    }

    init {
        progressBar.visibility = View.VISIBLE
    }
}