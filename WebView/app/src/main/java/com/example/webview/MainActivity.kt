package com.example.webview

import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O) //API version/level issue which is revoked here
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webViewVariable= findViewById<WebView>(R.id.webView)
        webViewSetUp(webViewVariable)
    }

    @RequiresApi(Build.VERSION_CODES.O) //API version/level issue which is revoked here
    private fun webViewSetUp(a: WebView){
        a.webViewClient= WebViewClient()

        a.apply {
            settings.javaScriptEnabled= true
            settings.safeBrowsingEnabled= true
            loadUrl("https://www.youtube.com/")
        }
    }
}