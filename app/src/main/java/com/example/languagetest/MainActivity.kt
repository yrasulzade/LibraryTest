package com.example.languagetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.languagetest.databinding.ActivityMainBinding
import com.example.whelp.model.Contact
import com.example.whelp.model.SkyTech
import com.example.whelp.model.UserCredentials

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val test =
            UserCredentials("en", Contact("enrike@whelp-test.co", "Enrike Nur",
                "+994703852755"))

        binding.button.setOnClickListener {
            SkyTech.Builder()
                .key("rPyCMWFMk27ucwYPpZE6")
                .appID("f4ee05e97792cf5a5f249c8af2fc8068")
                .userCredentials(test)
                .open(this)
        }
    }
}