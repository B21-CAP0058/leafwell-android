@file:Suppress("DEPRECATION")

package com.example.capstone.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.capstone.data.ApiConfig
import com.example.capstone.data.UserRequest
import com.example.capstone.data.UserResponse
import com.example.capstone.databinding.ActivityLoginBinding
import com.example.capstone.ui.home.HomeFragment
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

    }

    private fun clickButtonLogin() {
        binding.buttonSignIn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val req = UserRequest()
        req.email = binding.editTextEmail.text.toString().trim()
        req.password = binding.editTextPassword.text.toString().trim()

        if ((req.email.isNullOrEmpty()) || (req.password.isNullOrEmpty())) {

            val loginRes = MutableLiveData<UserResponse>()
            ApiConfig.getApiService().userLogin(req).enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        loginRes.value = response.body()
                        Log.d(TAG, "Successful ${response.code()}")

                        try {
                            val username = binding.editTextEmail.text.toString().trim()
                            val bundle = Bundle()
                            bundle.putString("edittext", username)
                            val fragobj = HomeFragment()
                            fragobj.arguments = bundle
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {

                        Log.d(
                            TAG,
                            "Error jika tdk sukses adalah ${response.errorBody().toString()}"
                        )
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d(TAG, "Gagal : ${t.message.toString()}")
                }
            })
        } else {
            Log.d(TAG, "Gagal password/email kosong ")
        }
    }


}