package com.app.retrofitwithcoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.app.daggrhiltdemo.base.BaseActivity
import com.app.retrofitwithcoroutines.repo.UserRepository
import com.app.retrofitwithcoroutines.databinding.ActivityMain2Binding
import com.app.retrofitwithcoroutines.session.SharedPreferenceHelper
import com.app.retrofitwithcoroutines.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    @Inject
    lateinit var userRepo: UserRepository

    private val base by lazy {
        BaseActivity(this)
    }

    private var onDoubleBackPress = false

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepo.userDetails()
        base.showProgress()

        binding.btnLogout.setOnClickListener {
            SharedPreferenceHelper(this).clear()
            base.showProgress()
            Handler(Looper.getMainLooper()).postDelayed({
                base.hideProgress()
                startActivity(Intent(this, MainActivity::class.java))
            }, 2000)

        }


        observer()
    }

    private fun observer() {
        userRepo.userDetailState.observe(this) {
            base.hideProgress()
            when (it) {
                is UserRepository.UserDetailSuccess -> {
                    Log.d("userApi", "obeserver: ${it.response}")
                    binding.tvName.text = "Name: " + it.response.name
                    binding.tvRole.text = "Role: " + it.response.role
                }

                is UserRepository.UserDetailError -> {
                    Log.d("userApi", "obeserver: ${it.error}")
                }

                else -> {
                    Log.d("error", "obeserver: error")
                    showToast("Something went wrong, please login again")
                    SharedPreferenceHelper(this@MainActivity2).clear()
                    startActivity(Intent(this@MainActivity2, MainActivity::class.java))
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