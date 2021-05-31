package com.example.capstone

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.source.Repository
import com.example.capstone.di.Injection
import com.example.capstone.ui.detail.DetailViewModel
import com.example.capstone.ui.searchmanual.SearchManualViewModel

class ViewModelFactory private constructor(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T: ViewModel> create(modelClass:Class<T>):T{
        return when{
            modelClass.isAssignableFrom(SearchManualViewModel::class.java) -> {
                SearchManualViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}