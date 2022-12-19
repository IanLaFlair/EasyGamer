package com.dicoding.tourismapp.core.utils

import com.ian.core.data.source.local.entity.GamesEntity
import com.ian.core.data.source.remote.response.GamesResponse
import com.ian.core.data.source.remote.response.GamesResult
import com.ian.core.domain.model.GamesModel


object DataMapper {
    fun mapResponsesToEntities(input: List<GamesResult>): List<GamesEntity> {
        val gamesList = ArrayList<GamesEntity>()
        input.map {
            val games = GamesEntity(
                gamesId = it.id,
                name = it.name,
                rating = it.rating,
                releaseDate = it.released,
                genre = it.genres[0].name,
                image = it.backgroundImage,
                isFavorite = false
            )
            gamesList.add(games)
        }
        return gamesList
    }

    fun mapEntitiesToDomain(input: List<GamesEntity>): List<GamesModel> =
        input.map {
            GamesModel(
                gamesId = it.gamesId,
                name = it.name,
                rating = it.rating,
                image = it.image,
                genre = it.genre,
                released_date = it.releaseDate,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: GamesModel) = GamesEntity(
        gamesId = input.gamesId,
        name = input.name,
        rating = input.rating,
        image = input.image,
        genre = input.genre,
        releaseDate = input.released_date,
        isFavorite = input.isFavorite
    )
}