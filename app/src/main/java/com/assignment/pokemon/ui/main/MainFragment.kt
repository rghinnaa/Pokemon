package com.assignment.pokemon.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.assignment.pokemon.R
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.databinding.FragmentMainBinding
import com.assignment.pokemon.ui.main.adapter.MainAdapter
import com.assignment.pokemon.ui.main.adapter.PagingLoadStateAdapter
import com.assignment.pokemon.utils.*
import com.assignment.pokemon.utils.Const.emptyString
import com.assignment.pokemon.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@SuppressLint("NotifyDataSetChanged")
class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding<FragmentMainBinding>()
    private val viewModel: MainViewModel by inject()

    private val pokemonAdapter by lazy {
        MainAdapter.pokemonAdapter(viewLifecycleOwner.lifecycle)
    }

    private var searchAdapter = MainAdapter.searchAdapter
    private var pokemonFavorite = ArrayList<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
        initViewModelCallback()

    }

    private fun initView() {
        initOnClick()
        initAdapter()
        initPagingAdapter()
        initAdapterListener()
        initTextDelayOnType()
        initSwipeRefresh()
        binding.etSearch.setOnEditorActionListener(onImeSearchClicked)
    }

    private fun initViewModel() {
        viewModel.getPokemonFavorite()
        initPokemonCallback()
    }

    private fun initViewModelCallback() {
        initPokemonFavoriteCallback()
        initSearchPokemonCallback()
    }

    private fun initAdapter() {
        binding.rvPokemon.adapter = pokemonAdapter.withLoadStateFooter(
            PagingLoadStateAdapter { pokemonAdapter.retry() }
        )
        binding.rvPokemonSearched.adapter = searchAdapter
    }

    private fun initAdapterListener() {
        pokemonAdapter.setOnItemClickListener { _, item, _ ->
            binding.etSearch.setText(emptyString)

            navController.navigateOrNull(
                MainFragmentDirections.actionDetailFragment(item.pokemonNumber)
            )
        }

        searchAdapter.setOnItemClickListener { _, item, _ ->
            val number = item.number ?: 0
            binding.etSearch.setText(emptyString)

            navController.navigateOrNull(
                MainFragmentDirections.actionDetailFragment(number)
            )
        }

        MainAdapter.setOnFavoriteListener { item, active ->
            val favorite = PokemonFavorite(
                name = item.pokemonName,
                number = item.pokemonNumber,
                image = item.pokemonImage,
                type1 = item.pokemonType.getOrNull(0),
                type2 = item.pokemonType.getOrNull(1)
            )

            if(active) {
                viewModel.insertPokemonFavorite(favorite)
            } else {
                viewModel.deletePokemonFavorite(item.pokemonNumber)
            }
        }

        MainAdapter.setOnSearchFavoriteListener { item, active ->
            val favorite = PokemonFavorite(
                name = item.name,
                number = item.number,
                image = item.image,
                type1 = item.type1,
                type2 = item.type2
            )

            if(active) {
                viewModel.insertPokemonFavorite(favorite)
            } else {
                viewModel.deletePokemonFavorite(item.number)
            }
        }
    }

    private fun initPagingAdapter() {
        pokemonAdapter.addLoadStateListener { loadState ->
            binding.apply {
                when(loadState.source.refresh) {
                    is LoadState.Loading -> {
                        swipeRefresh.isRefreshing = true
                        rvPokemon.isVisible = false
                        binding.rvPokemonSearched.isVisible = false
                        binding.clEmptySearch.isVisible = false
                    }
                    is LoadState.NotLoading -> {
                        swipeRefresh.isRefreshing = false
                        rvPokemon.isVisible = true
                        binding.rvPokemonSearched.isVisible = false
                        binding.clEmptySearch.isVisible = false
                    }
                    is LoadState.Error -> {
                        swipeRefresh.isRefreshing = false
                        rvPokemon.isVisible = false
                        binding.rvPokemonSearched.isVisible = false
                        binding.clEmptySearch.isVisible = false
                    }
                }
            }
        }
    }

    private fun initPokemonFavoriteCallback() {
        viewModel.pokemonFavorite.observe(viewLifecycleOwner) { result ->
            pokemonFavorite.clear()
            if(result.isNotEmpty()) {
                result.map {
                    pokemonFavorite.add(it.number ?: 0)
                }
                MainAdapter.setNumberFavorite(pokemonFavorite)
            } else {
                MainAdapter.setNumberFavorite(pokemonFavorite)
            }
        }
    }

    private fun initSearchPokemonCallback() {
        viewModel.searchPokemon.observe(viewLifecycleOwner) { result ->
            if(!binding.rvPokemon.isVisible) {
                if (result.isNotEmpty()) {
                    searchAdapter.items = result
                    searchAdapter.notifyDataSetChanged()

                    binding.clEmptySearch.isVisible = false
                } else if (result.isNullOrEmpty()) {
                    searchAdapter.items = emptyList()
                    searchAdapter.notifyDataSetChanged()

                    binding.clEmptySearch.isVisible = true
                }
            }
        }
    }

    private fun initPokemonCallback() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.requestPokemonList().observe(viewLifecycleOwner) { result ->
                pokemonAdapter.submitData(viewLifecycleOwner.lifecycle, result)
                pokemonAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initTextDelayOnType() {
        binding.apply {
            boxSearch.editText?.addDelayOnTypeWithScope(200, lifecycleScope) {
                if (etSearch.text?.isNotEmpty() == true) {
                    boxSearch.setEndIconDrawable(R.drawable.ic_baseline_cancel)
                } else boxSearch.setEndIconDrawable(R.drawable.ic_search)
                boxSearch.setEndIconOnClickListener {
                    etSearch.setText(emptyString)
                    boxSearch.setEndIconDrawable(R.drawable.ic_search)

                    binding.rvPokemon.isVisible  = true
                    binding.rvPokemonSearched.isVisible = false

                    initViewModel()
                }
            }
        }
    }

    private val onImeSearchClicked = TextView.OnEditorActionListener { v, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            val keyword = binding.etSearch.text.toString()
            binding.etSearch.clearFocus()

            if(keyword != emptyString) {
                binding.rvPokemon.isVisible  = false
                binding.rvPokemonSearched.isVisible = true

                viewModel.searchPokemon(keyword)
            } else {
                binding.rvPokemon.isVisible  = true
                binding.rvPokemonSearched.isVisible = false

                initViewModel()
            }

            activity?.hideKeyboard(v)

            return@OnEditorActionListener true
        }
        false
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
            ibFavorite.setOnClickListener(onCLickCallback)
        }
    }

    private val onCLickCallback = View.OnClickListener { view ->
        when (view) {
            binding.ibFavorite -> {
                binding.etSearch.setText(emptyString)

                navController.navigateOrNull(
                    MainFragmentDirections.actionFavoriteFragment()
                )
            }
        }
    }

}