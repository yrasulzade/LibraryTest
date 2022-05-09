package com.example.languagetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.languagetest.databinding.ActivityMainBinding
import com.example.whelp.SkyTech

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            SkyTech.Builder(applicationContext).open(this)
        }
    }
}