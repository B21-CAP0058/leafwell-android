package com.example.capstone.data.source.remote

import android.util.Log
import com.example.capstone.data.network.ApiConfig
import com.example.capstone.data.source.local.entity.DetailEntity
import com.example.capstone.data.source.local.entity.HerbListEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object{
        @Volatile
        private var instance:RemoteDataSource? = null

        fun getInstance():RemoteDataSource =
            instance?:synchronized(this){
                instance?:RemoteDataSource()
            }
    }

    fun getHerbs(callback:LoadHerbsCallback){
        val client = ApiConfig.getServiceApi().getHerbList()
        client.enqueue(object: Callback<HerbListResponse> {
            override fun onResponse(
                call: Call<HerbListResponse>,
                response: Response<HerbListResponse>
            ) {
                callback.herbLoaded(response.body()?.data)
            }

            override fun onFailure(call: Call<HerbListResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getHerbs onFailure : ${t.message}")
            }
        })
    }

    fun getHerbDetail(callback:LoadHerbDetailCallback,uuid:String){
        val client = ApiConfig.getServiceApi().getHerbDetail(uuid)
        client.enqueue(object:Callback<DetalResponse>{
            override fun onResponse(call: Call<DetalResponse>, response: Response<DetalResponse>) {
                callback.herbDetailLoaded(response.body()?.data)
            }

            override fun onFailure(call: Call<DetalResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "Search onFailure : ${t.message}")
            }

        })
    }

    fun search(callback:LoadHerbSearchCallback, keyword:String){
        val client = ApiConfig.getServiceApi().search(keyword)
        client.enqueue(object:Callback<HerbListResponse>{
            override fun onResponse(
                call: Call<HerbListResponse>,
                response: Response<HerbListResponse>
            ) {
                callback.herbSearchLoaded(response.body()?.data)
            }

            override fun onFailure(call: Call<HerbListResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "search onFailure : ${t.message}")
            }
        })
    }

    interface LoadHerbSearchCallback {
        fun herbSearchLoaded(herbsSearch:List<Herb>?)
    }

    interface LoadHerbDetailCallback {
        fun herbDetailLoaded(herbsDetail:Data?)
    }

    interface LoadHerbsCallback {
        fun herbLoaded(herbs:List<Herb>?)
    }
}