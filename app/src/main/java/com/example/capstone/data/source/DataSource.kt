package com.example.capstone.data.source

import androidx.lifecycle.LiveData
import com.example.capstone.data.source.local.entity.DetailEntity
import com.example.capstone.data.source.local.entity.HerbListEntity

interface DataSource {
    fun getHerbs(): LiveData<List<HerbListEntity>>
    fun getHerbDetail(uuid:String):LiveData<DetailEntity>
    fun search(keyword:String):LiveData<List<HerbListEntity>>
}