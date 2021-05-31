package com.example.capstone.ui.searchmanual

import androidx.lifecycle.ViewModel
import com.example.capstone.data.source.Repository

class SearchManualViewModel(private val repository: Repository): ViewModel() {
    fun getHerbs() = repository.getHerbs()
    fun search(keyword:String) = repository.search(keyword)

}