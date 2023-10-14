package com.app.retrofitwithcoroutines

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.app.daggrhiltdemo.base.BaseActivity
import com.app.retrofitwithcoroutines.databinding.ActivityMainBinding
import com.app.retrofitwithcoroutines.repo.UserRepository
import com.app.retrofitwithcoroutines.request_models.LoginRequest
import com.app.retrofitwithcoroutines.session.SharedPreferenceHelper
import com.app.retrofitwithcoroutines.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var userRepo: UserRepository

    private val base by lazy {
        BaseActivity(this)
    }

    private var onDoubleBackPress = false

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val loginRequest = LoginRequest(email = binding.etEmail.text.toString().trim(),
                password = binding.etPassword.text.toString().trim())
            base.showProgress()
            userRepo.login(loginRequest)
        }
        loginObserver()
    }

    private fun loginObserver() {
        userRepo.loginState.observe(this) {
            base.hideProgress()
            when (it) {
                is UserRepository.LoginSuccess -> {
                    Log.d("loginApi", "loginObserver: ${it.response}")

                    SharedPreferenceHelper(this).setValue("token", it.response.access_token)
                    SharedPreferenceHelper(this).setValue("isLogin", "true")
                    startActivity(Intent(this, MainActivity2::class.java))
                }

                is UserRepository.LoginError -> {
                    Log.d("loginApi", "loginObserver: ${it.error}")
                }
            }
        }
    }

    override fun onBackPressed() {
        if (onDoubleBackPress) {
            finishAffinity()
        }

        this.onDoubleBackPress = true
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_LONG).show()
        Handler(Looper.getMainLooper()).postDelayed({
            this.onDoubleBackPress = false
        }, 3000)
    }
}