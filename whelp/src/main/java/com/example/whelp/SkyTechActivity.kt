package com.example.whelp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whelp.databinding.ActivitySkyTechBinding
import com.example.whelp.model.UserCredentials
import com.example.whelp.util.CredentialHelper
import com.example.whelp.util.Preferences
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.codec.digest.HmacUtils
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

        binding.apply {
            whelpWebView.isVerticalScrollBarEnabled = true
            whelpWebView.isHorizontalScrollBarEnabled = true
            whelpWebView.settings.loadWithOverviewMode = true
            whelpWebView.settings.useWideViewPort = true
            whelpWebView.settings.javaScriptEnabled = true
        }

        val settings: WebSettings = binding.whelpWebView.settings
        settings.domStorageEnabled = true

        if (!isOnline()) {
            showMessage(getString(R.string.no_internet))

            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            return
        }

        if (isOnline()) loadWebView("https://widget-api.getwhelp.com/sdk/chat?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJl[…]waWQiOiIwIn19.2vUs3No1PLrUi9tWyT3wXdBNZVhkKPvyaVeuYkDUtmU")


        CredentialHelper.credential.observe(this) {
            Log.d("CredentialHelper000", "onViewCreated: " + it.api_key)
            Log.d("CredentialHelper000", "onViewCreated: " + it.app_id)
            Log.d("CredentialHelper000", "onViewCreated: " + it.userCredentials!!.language)

            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<UserCredentials> =
                moshi.adapter(UserCredentials::class.java)

            val json = jsonAdapter.toJson(it.userCredentials)

            hmacWithApacheCommons("HmacSHA256", json.toString(), "${it.api_key}${it.app_id}")
        }
    }

    private fun loadWebView(token: String) {
        binding.whelpWebView.loadUrl(token)
        binding.whelpWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
//                view?.loadUrl(url)

                //if (url.contains("widget.dev-whelp.com/sdk/chat?token")) {
                view?.loadUrl(url)
//                } else {
//                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                    startActivity(i)
//                }


                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                showLoading()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                hideLoading()
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                val errorMessage = "Got Error! $error"
                showToast(errorMessage)
                //infoTV.text = errorMessage
                hideLoading()
                super.onReceivedError(view, request, error)
            }
        }
    }

    private fun hmacWithApacheCommons(
        algorithm: String?,
        data: String?,
        key: String?
    ): String? {
        return HmacUtils(algorithm, key).hmacHex(data)
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
    private fun hideLoading(){
        binding.progressBar.visibility = View.GONE
    }

    private fun showLoading(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showMessage(message: String) {
        val parentLayout = findViewById<View>(android.R.id.content)
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()
    }
}