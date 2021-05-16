package com.example.capstone.ui.searchmanual

import androidx.lifecycle.ViewModel
import com.example.capstone.data.DummyData
import com.example.capstone.data.DummyEntity

class SearchManualViewModel: ViewModel() {
    fun getDummyData():List<DummyEntity> = DummyData.generateDummyData()
}