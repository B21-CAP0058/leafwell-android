package com.example.capstone.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.capstone.R
import com.example.capstone.ViewModelFactory
import com.example.capstone.data.source.local.entity.DetailEntity
import com.example.capstone.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val herbId = extras.getString(EXTRA_ID)
            if (herbId != null) {
                viewModel.getHerb(herbId)
                viewModel.getHerbDetail().observe(this, { detail ->
                    binding.progressBar.isVisible = false
                    populateHerb(detail)
                })

            }
        }
        supportActionBar?.title = "Detail"

    }

    private fun populateHerb(detail: DetailEntity) {
        binding.tvTitle.text = detail.name
        binding.tvDescription.text = detail.description
        binding.tvEfficacy.text = detail.efficacy
        binding.tvRecipt.text = detail.recipt
        var backdrop = 0
        when(detail.uuid){
            "1" -> backdrop = R.drawable.bayam_hijau
            "2" -> backdrop = R.drawable.bayam_malabar
            "3" -> backdrop = R.drawable.ara
            "4" -> backdrop = R.drawable.daun_kelor
            "5" -> backdrop = R.drawable.daun_kersen
            "6" -> backdrop = R.drawable.daun_kari
            "7" -> backdrop = R.drawable.srigading
            "8" -> backdrop = R.drawable.ruku_ruku
            "9" -> backdrop = R.drawable.daun_sirih
            "10" -> backdrop = R.drawable.daun_jambu_biji
        }
        Glide.with(this)
            .load(backdrop)
            .apply(RequestOptions.overrideOf(400,300))
            .into(binding.ivBackdropPoster)
    }
}