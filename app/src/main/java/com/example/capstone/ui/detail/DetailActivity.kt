package com.example.capstone.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        var isFavorite = false
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
                    populateHerb(detail)

                })

            }
        }
        supportActionBar?.title = "Detail"
        binding.ivFavorite.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                binding.ivFavorite.setBackgroundResource(R.drawable.ic_favorite_true_24)
                Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show()
            } else {
                binding.ivFavorite.setBackgroundResource(R.drawable.ic_favorite_false_24)
                Toast.makeText(this, "Not Favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateHerb(detail: DetailEntity) {
        binding.tvTitle.text = detail.name
        binding.tvDescription.text = detail.description
        binding.tvEfficacy.text = detail.efficacy
        binding.tvRecipt.text = detail.recipt
        Glide.with(this)
            .load(detail.image)
            .apply(RequestOptions.overrideOf(180,45))
            .into(binding.ivBackdropPoster)
    }
}