package com.example.capstone.ui.searchmanual

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.ListItemAdapter
import com.example.capstone.ViewModelFactory
import com.example.capstone.databinding.ActivitySearchManualBinding

class SearchManualActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchManualBinding
    private lateinit var viewModel: SearchManualViewModel
    private lateinit var listAdapter: ListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchManualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Search"
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[SearchManualViewModel::class.java]
        listAdapter = ListItemAdapter()

        listItems()

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.isNotEmpty()){
                    viewModel.search(newText)
                }else{
                    listItems()
                }
                return false
            }
        })

        with(binding.rvItemList) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = listAdapter
        }

    }

    private fun listItems() {
        viewModel.getHerbs().observe(this, { herbs ->
            listAdapter.setItem(herbs)
            listAdapter.notifyDataSetChanged()
        })
    }

}