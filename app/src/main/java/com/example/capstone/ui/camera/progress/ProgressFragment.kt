package com.example.capstone.ui.camera.progress

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capstone.R
import com.example.capstone.databinding.FragmentProgressBinding
import com.example.capstone.ui.detail.DetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProgressFragment : Fragment() {

    private lateinit var binding:FragmentProgressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgressBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        binding.progressBar.max = 1000
        val currentProgress = 200

        ObjectAnimator.ofInt(R.id.progress_bar,"progress",currentProgress)
            .setDuration(2000)
            .start()
        startIdentify()
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    private fun startIdentify() {
        GlobalScope.launch(Dispatchers.Main){
            delay(4000) //representing process of ML to give output
            startActivity(Intent(context, DetailActivity::class.java))
            activity?.finish()
        }
    }

}