package com.ian.easygamer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ian.core.domain.usecase.GamesUsecase

class HomeViewModel(gamesUseCase: GamesUsecase) : ViewModel() {
    val games = gamesUseCase.getAllGames().asLiveData()
}