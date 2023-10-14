package com.app.retrofitwithcoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.app.retrofitwithcoroutines.databinding.ActivitySplashBinding
import com.app.retrofitwithcoroutines.session.SharedPreferenceHelper

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val isLogin = SharedPreferenceHelper(this).getValue("isLogin")
        // handling login
        Handler(Looper.getMainLooper()).postDelayed({
            if (isLogin == "true") {
                startActivity(Intent(this, MainActivity2::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }, 3000)
    }
}