package com.example.capstone.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.MlActivity
import com.example.capstone.databinding.ActivityHomeBinding
import com.example.capstone.ui.searchmanual.SearchManualActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cvMenu1.setOnClickListener {
            val intent = Intent(this, SearchManualActivity::class.java)
            startActivity(intent)
        }

        binding.cvMenu2.setOnClickListener {
            startActivity(Intent(this, MlActivity::class.java))
        }
    }
}