package com.ian.core.data

import com.dicoding.tourismapp.core.utils.AppExecutors
import com.dicoding.tourismapp.core.utils.DataMapper
import com.ian.core.data.source.local.LocalDataSource
import com.ian.core.data.source.remote.RemoteDataSource
import com.ian.core.data.source.remote.network.ApiResponse
import com.ian.core.data.source.remote.response.GamesResult
import com.ian.core.domain.model.GamesModel
import com.ian.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GamesRepository(private val remoteDataSource: RemoteDataSource,
                      private val localDataSource: LocalDataSource,
                      private val appExecutors: AppExecutors
) : IGamesRepository {

    override fun getAllGames(): Flow<Resource<List<GamesModel>>> =
        object : NetworkBoundResource<List<GamesModel>, List<GamesResult>>() {
            override fun loadFromDB(): Flow<List<GamesModel>> {
                return localDataSource.getAllGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GamesModel>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<GamesResult>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<GamesResult>) {
                val gamesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(gamesList)
            }
        }.asFlow()

    override fun getFavouriteGames(): Flow<List<GamesModel>> {
        return localDataSource.getFavouriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavouriteGames(tourism: GamesModel, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavouriteGames(tourismEntity, state) }
    }
}