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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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

            homeViewModel.games.observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding.pbHome.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbHome.visibility = View.GONE
                            gamesAdapter.setData(it.data)
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

            with(binding.rvgames) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = gamesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}