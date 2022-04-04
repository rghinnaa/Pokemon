package com.assignment.pokemon.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.assignment.pokemon.R
import com.assignment.pokemon.databinding.FragmentFavoriteBinding
import com.assignment.pokemon.ui.main.adapter.MainAdapter
import com.assignment.pokemon.utils.navController
import com.assignment.pokemon.utils.navigateOrNull
import com.assignment.pokemon.utils.viewBinding
import com.assignment.pokemon.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

@SuppressLint("NotifyDataSetChanged")
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding by viewBinding<FragmentFavoriteBinding>()
    private val viewModel: MainViewModel by inject()

    private var favoriteAdapter = MainAdapter.favoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
        initViewModelCallback()

    }

    private fun initView() {
        initOnClick()
        initAdapter()
        initAdapterListener()
        initSwipeRefresh()
    }

    private fun initViewModel() {
        viewModel.getPokemonFavorite()
    }

    private fun initViewModelCallback() {
        initPokemonCallback()
    }

    private fun initAdapter() {
        binding.rvFavorite.adapter = favoriteAdapter
    }

    private fun initAdapterListener() {
        favoriteAdapter.setOnItemClickListener { _, item, _ ->
            val number = item.number ?: 0

            navController.navigateOrNull(
                FavoriteFragmentDirections.actionDetailFragment(number)
            )
        }

        MainAdapter.setOnButtonFavoriteListener { item, isChecked ->
            if(!isChecked) viewModel.deletePokemonFavorite(item.number)
        }
    }

    private fun initPokemonCallback() {
        viewModel.pokemonFavorite.observe(viewLifecycleOwner) { result ->
            favoriteAdapter.items = result
            favoriteAdapter.notifyDataSetChanged()

            binding.clEmptyFavorite.isVisible = result.isNullOrEmpty()
        }
    }

    private fun initSwipeRefresh() {
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                initViewModel()
                isRefreshing = false
            }
        }
    }

    private fun initOnClick() {
        binding.apply {
            ibBack.setOnClickListener(onCLickCallback)
        }
    }

    private val onCLickCallback = View.OnClickListener { view ->
        when (view) {
            binding.ibBack -> {
                navController.navigateUp()
            }
        }
    }

}