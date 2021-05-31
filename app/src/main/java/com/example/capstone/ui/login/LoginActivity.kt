@file:Suppress("DEPRECATION")

package com.example.capstone.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.MainActivity
import com.example.capstone.data.network.ApiConfig
import com.example.capstone.data.SharedPrefManager
import com.example.capstone.data.UserResponse
import com.example.capstone.databinding.ActivityLoginBinding
import com.example.capstone.ui.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Login Activity"
    }

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickButtonLogin()
        register()

    }

    private fun register() {

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun clickButtonLogin() {
        binding.btnLogin.setOnClickListener {
//            loginUser()
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {

        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.etEmail.error = "Email required"
            binding.etEmail.requestFocus()
            return
        }


        if (password.isEmpty()) {
            binding.etPassword.error = "Password required"
            binding.etPassword.requestFocus()
            return
        }
        ApiConfig.getApiService().userLogin(email,password)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){

                        Toast.makeText(applicationContext, "Login berhasil", Toast.LENGTH_SHORT).show()
                        SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)
                    }else{
                        Log.e(TAG,response.body().toString())
                        Toast.makeText(applicationContext, "Maaf Email atau password keliru", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.e(TAG, it) }
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })

    }
//    override fun onStart() {
//        super.onStart()
//
//        if(SharedPrefManager.getInstance(this).isLoggedIn){
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//
//            startActivity(intent)
//        }
//    }


}