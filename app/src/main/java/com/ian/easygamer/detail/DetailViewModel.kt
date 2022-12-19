package com.ian.easygamer.detail

import androidx.lifecycle.ViewModel
import com.ian.core.domain.model.GamesModel
import com.ian.core.domain.usecase.GamesUsecase

class DetailViewModel(private val gamesUsecase: GamesUsecase): ViewModel() {
    fun setFavouriteGames(games: GamesModel, newStatus:Boolean) =
        gamesUsecase.setFavouriteGames(games, newStatus)
}