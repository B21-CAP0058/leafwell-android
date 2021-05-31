package com.example.capstone.di

import android.content.Context
import com.example.capstone.data.source.Repository
import com.example.capstone.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): Repository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }
}