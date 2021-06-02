package com.example.capstone.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.capstone.R
import com.example.capstone.databinding.ActivityGalleryBinding
import com.example.capstone.ui.progress.ProgressFragment

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding

    companion object {
        const val REQUEST_CODE = 200

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressFragment = ProgressFragment()
        binding.btnAnalyze.isEnabled = false

        binding.ivPhoto.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, REQUEST_CODE)
        }

        binding.btnAnalyze.setOnClickListener {
            if (binding.ivPhoto.drawable !=null) {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_up, R.anim.anim_slide_out_down)
                    .replace(R.id.fragment_container, progressFragment)
                    .commit()
            } else {
                Toast.makeText(this,"Please, insert image first",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.ivPhoto.setImageURI(data?.data)
        if(binding.ivPhoto.drawable !=null){
            binding.btnAnalyze.isEnabled = true
            binding.tvTapHere.isVisible = false
        }


    }
}