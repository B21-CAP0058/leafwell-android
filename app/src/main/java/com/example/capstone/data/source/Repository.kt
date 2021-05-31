package com.example.capstone.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.data.source.local.entity.DetailEntity
import com.example.capstone.data.source.local.entity.HerbListEntity
import com.example.capstone.data.source.remote.Data
import com.example.capstone.data.source.remote.Herb
import com.example.capstone.data.source.remote.RemoteDataSource

class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource {

    val herbResult = MutableLiveData<List<HerbListEntity>>()

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData)
            }
    }

    override fun getHerbs(): LiveData<List<HerbListEntity>> {
        remoteDataSource.getHerbs(object : RemoteDataSource.LoadHerbsCallback {
            override fun herbLoaded(herbs: List<Herb>?) {
                val herbList = ArrayList<HerbListEntity>()
                if (herbs != null) {
                    for (response in herbs) {
                        with(response) {
                            val herb = HerbListEntity(image, name, uuid)
                            herbList.add(herb)
                        }
                        herbResult.postValue(herbList)
                    }
                }
            }
        })
        return herbResult
    }

    override fun getHerbDetail(uuid: String): LiveData<DetailEntity> {
        val herbDetailResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getHerbDetail(object : RemoteDataSource.LoadHerbDetailCallback {
            override fun herbDetailLoaded(herbsDetail: Data?) {
                if (herbsDetail != null) {
                        val herbDetails = DetailEntity(
                            image = herbsDetail.image,
                            isFavorited = herbsDetail.isFavorited,
                            name = herbsDetail.name,
                            description = herbsDetail.description,
                            createdAt = herbsDetail.createdAt,
                            recipt = herbsDetail.recipt,
                            uuid = herbsDetail.uuid,
                            efficacy = herbsDetail.efficacy,
                            tags = herbsDetail.tags
                        )
                        herbDetailResult.postValue(herbDetails)
                    }
            }

        }, uuid)
        return herbDetailResult
    }

    override fun search(keyword: String): LiveData<List<HerbListEntity>> {

        remoteDataSource.search(object : RemoteDataSource.LoadHerbSearchCallback {
            override fun herbSearchLoaded(herbsSearch: List<Herb>?) {
                val herbList = ArrayList<HerbListEntity>()
                if (herbsSearch != null) {
                    for (response in herbsSearch) {
                        with(response) {
                            val herb = HerbListEntity(image, name, uuid)
                            herbList.add(herb)
                        }
                        herbResult.postValue(herbList)
                    }
                }
            }

        }, keyword)
        return herbResult

    }
}