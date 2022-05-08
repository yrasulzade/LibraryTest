package com.example.whelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class WhelpLauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whelp_launcher)

        Toast.makeText(this, "This is 1.1 version", Toast.LENGTH_LONG).show()
    }
}