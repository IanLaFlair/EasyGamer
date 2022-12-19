package com.ian.core.data.source.remote

import android.util.Log
import com.ian.core.data.source.remote.network.ApiResponse
import com.ian.core.data.source.remote.network.ApiService
import com.ian.core.data.source.remote.response.GamesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllGames(): Flow<ApiResponse<List<GamesResult>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList("546f5ddc106c473a9b206a5e17320717")
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}

