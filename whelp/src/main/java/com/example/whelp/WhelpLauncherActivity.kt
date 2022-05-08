package com.example.whelp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WhelpLauncherActivity : AppCompatActivity() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whelp_launcher)

        var version = preferences.getFromPrefs("version", 1) as Int

        val textView: TextView = findViewById(R.id.textView)
        val intentTextView: TextView = findViewById(R.id.key)

        textView.text = "version $version"

        preferences.saveToPrefs("version", ++version)

        Toast.makeText(this, "This is 1.2 version", Toast.LENGTH_LONG).show()

        val key = intent.extras?.get("key")
        intentTextView.text = key.toString()


    }
}