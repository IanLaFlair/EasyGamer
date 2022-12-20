package com.ian.easygamer.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ian.core.data.Resource
import com.ian.core.ui.GamesAdapter
import com.ian.easygamer.detail.DetailActivity
import com.ian.easygamer.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHome : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val gamesAdapter = GamesAdapter()
            gamesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
            with(binding.rvgames) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = gamesAdapter
            }

            homeViewModel.games.observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding.pbHome.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbHome.visibility = View.GONE
                            gamesAdapter.submitList(it.data)
                        }
                        is Resource.Error -> {
                            binding.pbHome.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "Something Went Wrong",
                                Toast.LENGTH_SHORT
                            )
                        }
                    }
                }
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}