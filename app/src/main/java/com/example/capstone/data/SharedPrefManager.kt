package com.example.capstone.data

import android.annotation.SuppressLint
import android.content.Context

class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: User
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences.getString("dateJoined", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("first_name", null),
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getBoolean("isActive", false),
                sharedPreferences.getString("lastLogin", null),
                sharedPreferences.getString("lastName", null),
                sharedPreferences.getString("url", null),
                sharedPreferences.getString("username", null)
            )
        }


    fun saveUser(user: User) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("dateJoined", user.dateJoined)
        editor.putString("email", user.email)
        editor.putString("firstName", user.firstName)
        editor.putInt("id", user.id)
        editor.putBoolean("isActive",user.isActive)
        editor.putString("lastLogin", user.lastLogin)
        editor.putString("lastName", user.lastName)
        editor.putString("url", user.url)
        editor.putString("username", user.username)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "my_shared_preff"
        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}