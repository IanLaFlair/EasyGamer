package com.ian.easygamer.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ian.core.domain.usecase.GamesUsecase

class FavoriteViewModel (gamesUsecase: GamesUsecase) : ViewModel() {
    val favoriteGames = gamesUsecase.getFavouriteGames().asLiveData()
}