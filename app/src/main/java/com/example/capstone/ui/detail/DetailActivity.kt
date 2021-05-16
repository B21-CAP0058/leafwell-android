package com.example.capstone.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.capstone.R
import com.example.capstone.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding

    companion object {
        const val EXTRA_ID = "extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        var isFavorite = false
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title="Detail"
        binding.ivFavorite.setOnClickListener {
            isFavorite = !isFavorite
            if(isFavorite){
                binding.ivFavorite.setBackgroundResource(R.drawable.ic_favorite_true_24)
                Toast.makeText(this,"Favorited",Toast.LENGTH_SHORT).show()
            } else{
                binding.ivFavorite.setBackgroundResource(R.drawable.ic_favorite_false_24)
                Toast.makeText(this,"Not Favorite",Toast.LENGTH_SHORT).show()
            }
        }
    }
}