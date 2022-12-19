package com.ian.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GamesModel(
    val gamesId: Int,
    val name: String,
    val image: String,
    val rating: Double,
    val released_date: String,
    val genre: String,
    val isFavorite: Boolean
): Parcelable
