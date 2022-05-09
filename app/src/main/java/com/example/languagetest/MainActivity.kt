package com.example.languagetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.languagetest.databinding.ActivityMainBinding
import com.example.whelp.model.Contract
import com.example.whelp.model.SkyTech
import com.example.whelp.model.UserCredentials

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val test =
            UserCredentials("az", Contract("test@box.az", "Rasul Mammadov",
                "994708306405", "", ""))

        binding.button.setOnClickListener {
            SkyTech.Builder()
                .key("yunis")
                .appID("app_id")
                .userCredentials(test)
                .open(this)
        }
    }
}