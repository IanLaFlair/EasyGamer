package com.ian.easygamer.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.ian.core.ui.GamesAdapter
import com.ian.easygamer.databinding.ActivityFavoriteBinding
import com.ian.easygamer.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFavorite()
    }

    private fun showFavorite(){
        val gamesAdapter = GamesAdapter()
        gamesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteGames.observe(this) { games ->
            gamesAdapter.setData(games)
            binding.txtWarn.visibility = if (games.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvgames) {
            layoutManager = GridLayoutManager(this@FavoriteActivity,2)
            setHasFixedSize(true)
            adapter = gamesAdapter
        }
    }
}