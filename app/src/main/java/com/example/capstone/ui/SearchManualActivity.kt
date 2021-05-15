package com.example.capstone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.databinding.ActivitySearchManualBinding

class SearchManualActivity : AppCompatActivity() {
    private lateinit var binding :ActivitySearchManualBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchManualBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}