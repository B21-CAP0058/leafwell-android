package com.example.capstone.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.data.source.Repository
import com.example.capstone.data.source.local.entity.DetailEntity

class DetailViewModel(private val repository: Repository): ViewModel() {
    private lateinit var herbDetail : LiveData<DetailEntity>
    fun getHerb(uuid:String){
        herbDetail = repository.getHerbDetail(uuid)
    }
    fun getHerbDetail() = herbDetail
}