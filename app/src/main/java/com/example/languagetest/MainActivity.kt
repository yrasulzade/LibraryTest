package com.example.languagetest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.languagetest.databinding.ActivityMainBinding
import com.example.whelp.Launcher
import com.example.whelp.WhelpLauncherActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, WhelpLauncherActivity::class.java)
            intent.putExtra("key","This is a key")
            startActivity(intent)
        }
    }
}