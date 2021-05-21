package com.example.capstone.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.databinding.FragmentHomeBinding
import com.example.capstone.ui.camera.CameraActivity
import com.example.capstone.ui.searchmanual.SearchManualActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        try {
            val message = requireArguments().getString("edittext")
            if (message != null) {
                //  txtMessageF.setText(message)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        binding.cvMenu1.setOnClickListener {
            val intent = Intent(context, SearchManualActivity::class.java)
            startActivity(intent)
        }

        binding.cvMenu2.setOnClickListener {
            val intent = Intent(context, CameraActivity::class.java)
        startActivity(intent)}
        return binding.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}