package com.ian.core.domain.usecase

import com.ian.core.data.GamesRepository
import com.ian.core.data.Resource
import com.ian.core.domain.model.GamesModel
import com.ian.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gamesRepository: IGamesRepository): GamesUsecase {

    override fun getAllGames() = gamesRepository.getAllGames()

    override fun getFavouriteGames() = gamesRepository.getFavouriteGames()

    override fun setFavouriteGames(games: GamesModel, state: Boolean) = gamesRepository.setFavouriteGames(games, state)
}