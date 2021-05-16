package com.example.capstone.ui.searchmanual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.ListItemAdapter
import com.example.capstone.databinding.ActivitySearchManualBinding

class SearchManualActivity : AppCompatActivity() {
    private lateinit var binding :ActivitySearchManualBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchManualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title="Search"

        val viewModel = ViewModelProvider(this,ViewModelProvider.
        NewInstanceFactory())[SearchManualViewModel::class.java]

        val dummyData = viewModel.getDummyData()
        val listAdapter = ListItemAdapter()

        listAdapter.setItem(dummyData)

        with(binding.rvItemList){
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = listAdapter
        }

    }
}