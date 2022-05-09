package com.example.whelp

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.whelp.databinding.ActivitySkyTechBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SkyTechActivity : AppCompatActivity() {
    @Inject
    lateinit var preferences: Preferences

    private lateinit var binding: ActivitySkyTechBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkyTechBinding.inflate(layoutInflater)

        setContentView(binding.root)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_sky_tech)

        binding.whelpWebView.settings.javaScriptEnabled = true

        binding.whelpWebView.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl("https://www.google.com")
                return true
            }
        }
        binding.whelpWebView.loadUrl("https://www.google.com")
    }

}