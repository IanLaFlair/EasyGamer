package com.ian.easygamer.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ian.core.domain.model.GamesModel
import com.ian.easygamer.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val detailGames = intent.getParcelableExtra<GamesModel>(EXTRA_DATA)
        showDetailGames(detailGames)

        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun showDetailGames(detailGames: GamesModel?) {
        detailGames?.let {
            binding.txtTitle.text = detailGames.name
            binding.txtGenre.text = detailGames.genre
            binding.txtRating.text = detailGames.rating.toString()
            binding.txtReleaseDate.text = detailGames.released_date
            Glide.with(this@DetailActivity)
                .load(detailGames.image)
                .into(binding.imgCover)

            var statusFavorite = detailGames.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavouriteGames(detailGames, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }
    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFavorite.setText("Hapus Sebagai Favorit")
        } else {
            binding.btnFavorite.setText("Simpan Sebagai Favorit")
        }
    }

}