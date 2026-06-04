package com.example.securewebbrowserapplication.screens

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.securewebbrowserapplication.databinding.ActivityWebViewScreenBinding

class WebViewScreen : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("URL")

        // WebView Settings
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true

        // Show Progress Bar initially
        binding.progressBar.visibility = View.VISIBLE

        // Progress Tracking
        binding.webView.webChromeClient =
            object : WebChromeClient() {

                override fun onProgressChanged(
                    view: WebView?,
                    newProgress: Int
                ) {

                    binding.progressBar.progress = newProgress

                    if (newProgress == 100) {
                        binding.progressBar.visibility = View.GONE
                    } else {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }



        // WebView Client
        binding.webView.webViewClient =
            object : WebViewClient() {

                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: android.graphics.Bitmap?
                ) {
                    super.onPageStarted(view, url, favicon)

                    binding.tvUrl.text = url
                }

                override fun onPageFinished(
                    view: WebView?,
                    url: String?
                ) {
                    super.onPageFinished(view, url)

                    val prefs =
                        getSharedPreferences(
                            "history",
                            MODE_PRIVATE
                        )

                    val urls =
                        prefs.getStringSet(
                            "urls",
                            mutableSetOf()
                        ) ?: mutableSetOf()

                    url?.let {

                        urls.add(it)

                        prefs.edit()
                            .putStringSet(
                                "urls",
                                urls
                            )
                            .apply()
                    }

                    val currentTime = System.currentTimeMillis()

                    val historyItem =
                        "${url}|${currentTime}"





                    binding.tvUrl.text = url
                    binding.progressBar.visibility = View.GONE
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: android.webkit.WebResourceError?
                ) {

                    if (request?.isForMainFrame == true) {

                        binding.progressBar.visibility = View.GONE

                        binding.tvUrl.text = "Failed to Load"

                        Toast.makeText(
                            this@WebViewScreen,
                            "Invalid URL or No Internet Connection",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        // Load URL
        if (!url.isNullOrEmpty()) {
            binding.webView.visibility = View.VISIBLE
            binding.webView.loadUrl(url)
        }

        // Top Bar Back Button
        binding.closeBt.setOnClickListener {

            finish()
        }
        binding.backBt.setOnClickListener {

            if (binding.webView.canGoBack()) {

                binding.webView.goBack()

            } else {

                finish()
            }
        }

        // Device Back Button
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {

                    if (binding.webView.canGoBack()) {

                        binding.webView.goBack()

                    } else {

                        finish()
                    }
                }
            }
        )
    }
}