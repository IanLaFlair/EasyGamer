package com.ian.core.data.source.local

import com.ian.core.data.source.local.entity.GamesEntity
import com.ian.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gamesDao: GamesDao) {

    fun getAllGames(): Flow<List<GamesEntity>> = gamesDao.getAllGames()

    fun getFavouriteGames(): Flow<List<GamesEntity>> = gamesDao.getFavouriteGames()

    suspend fun insertGames(gamesList: List<GamesEntity>) = gamesDao.insertGames(gamesList)

    fun setFavouriteGames(games: GamesEntity, newState: Boolean) {
        games.isFavorite = newState
        gamesDao.updateFavouriteGames(games)
    }
}