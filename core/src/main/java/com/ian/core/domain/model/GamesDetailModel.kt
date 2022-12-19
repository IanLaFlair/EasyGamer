package com.ian.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GamesDetailModel(
    val gamesId: Int,
    val name: String,
    val image: String,
    val rating: Double,
    val isFavorite: Boolean
): Parcelable
