package com.ian.easygamer.di

import com.ian.core.domain.usecase.GamesInteractor
import com.ian.core.domain.usecase.GamesUsecase
import com.ian.easygamer.detail.DetailViewModel
import com.ian.easygamer.favorite.FavoriteViewModel
import com.ian.easygamer.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUsecase> { GamesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}