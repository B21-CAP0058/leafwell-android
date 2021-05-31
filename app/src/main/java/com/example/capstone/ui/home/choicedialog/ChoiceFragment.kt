package com.example.capstone.ui.home.choicedialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.capstone.MlActivity
import com.example.capstone.R
import com.example.capstone.databinding.FragmentChoiceBinding
import com.example.capstone.ui.camera.CameraActivity
import com.example.capstone.ui.gallery.GalleryActivity

open class ChoiceFragment : DialogFragment() {

    private var _binding: FragmentChoiceBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChoiceBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        binding.ibCamera.setOnClickListener {
            val intent = Intent(context, CameraActivity::class.java)
            startActivity(intent)
            dialog?.dismiss()
        }
        binding.ibGalery.setOnClickListener {
            val intent = Intent(context, MlActivity::class.java)
            startActivity(intent)
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}