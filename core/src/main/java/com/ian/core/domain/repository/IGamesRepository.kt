package com.ian.core.domain.repository

import com.ian.core.data.Resource
import com.ian.core.domain.model.GamesModel
import kotlinx.coroutines.flow.Flow

interface  IGamesRepository {
    fun getAllGames(): Flow<Resource<List<GamesModel>>>
    fun getFavouriteGames(): Flow<List<GamesModel>>
    fun setFavouriteGames(tourism: GamesModel, state: Boolean)
}