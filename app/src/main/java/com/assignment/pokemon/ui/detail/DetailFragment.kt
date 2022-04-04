package com.assignment.pokemon.ui.detail

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.assignment.pokemon.R
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.data.model.mapper.PokemonDetail
import com.assignment.pokemon.data.model.mapper.PokemonSpeciesDetail
import com.assignment.pokemon.data.remote.Result
import com.assignment.pokemon.databinding.FragmentDetailBinding
import com.assignment.pokemon.ui.detail.adapter.InfoMenuAdapter
import com.assignment.pokemon.utils.capital
import com.assignment.pokemon.utils.colorStateList
import com.assignment.pokemon.utils.navController
import com.assignment.pokemon.utils.viewBinding
import com.assignment.pokemon.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding<FragmentDetailBinding>()
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: MainViewModel by inject()

    private var pokemonDetail = PokemonDetail()
    private var pokemonSpeciesDetail = PokemonSpeciesDetail()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
        initViewModelCallback()

    }

    private fun initView() {
        initOnClick()
        initAdapter()
        initSwipeRefresh()
    }

    private fun initViewModel() {
        viewModel.requestPokemon(args.pokemonId)
    }

    private fun initViewModelCallback() {
        initPokemonCallback()
        initPokemonSpeciesCallback()
        initPokemonFavoriteCallback()
    }

    private fun initAdapter() {
    }

    private fun initPokemonCallback() {
        viewModel.pokemon.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                is Result.Success -> {
                    binding.apply {
                        swipeRefresh.isRefreshing = false

                        pokemonDetail = result.data ?: PokemonDetail()

                        result.data?.let { item ->

                            tvName.text = item.pokemonName.capital()
                            tvNumber.text = root.context.getString(
                                R.string.hashtag,
                                item.pokemonNumber.toString()
                            )

                            cgType.removeAllViews()
                            item.pokemonType.forEach {
                                val chipDrawable =
                                    ChipDrawable.createFromAttributes(
                                        requireContext(),
                                        null,
                                        0,
                                        R.style.Chip_Filter
                                    )

                                val chip = Chip(context).apply {
                                    setChipDrawable(chipDrawable)
                                    text = it.capital()
                                    chipBackgroundColor = context.colorStateList(R.color.white)
                                    chipStrokeWidth = 1f
                                    chipStrokeColor = context.colorStateList(R.color.dove_gray)

                                    isClickable = false
                                    isFocusable = false

                                }

                                cgType.addView(chip)
                            }

                            Glide.with(root.context)
                                .asBitmap()
                                .load(item.pokemonImage)
                                .listener(object : RequestListener<Bitmap> {
                                    override fun onResourceReady(
                                        resource: Bitmap?,
                                        model: Any?,
                                        target: Target<Bitmap>?,
                                        dataSource: DataSource?,
                                        isFirstResource: Boolean
                                    ): Boolean {
                                        if (resource != null) {
                                            Palette.Builder(resource).generate { builder ->
                                                builder?.let { palette ->

                                                    clDetail.setBackgroundColor(
                                                        palette.getLightMutedColor(
                                                            Color.GRAY
                                                        )
                                                    )

                                                    tlInfo.setSelectedTabIndicatorColor(
                                                        palette.getLightMutedColor(
                                                            Color.GRAY
                                                        )
                                                    )
                                                }
                                            }
                                        }

                                        return false
                                    }

                                    override fun onLoadFailed(
                                        e: GlideException?,
                                        model: Any?,
                                        target: Target<Bitmap>?,
                                        isFirstResource: Boolean
                                    ): Boolean {
                                        return false
                                    }
                                })
                                .into(ivPokemon)


                            viewModel.getPokemonFavorite()
                        }
                    }
                }
                is Result.Error<*> -> {
                }
                else -> {}
            }
        }
    }

    private fun initPokemonSpeciesCallback() {
        viewModel.pokemonSpecies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {}
                is Result.Success -> {
                    pokemonSpeciesDetail = result.data ?: PokemonSpeciesDetail()

                    initTabLayout()
                }
                is Result.Error<*> -> {}
                else -> {}
            }
        }
    }

    private fun initPokemonFavoriteCallback() {
        viewModel.pokemonFavorite.observe(viewLifecycleOwner) { result ->
            if(result.isNotEmpty()) {
                val available = result.any { it.number == pokemonDetail.pokemonNumber }
                binding.cbFavorite.isChecked = available
            } else {
                binding.cbFavorite.isChecked = false
            }
        }
    }

    private fun initTabLayout() {
        binding.vpInfo.apply {
            val infoTitle = resources.getStringArray(R.array.infoTitle)
            adapter = InfoMenuAdapter(
                this@DetailFragment,
                Gson().toJson(pokemonDetail),
                Gson().toJson(pokemonSpeciesDetail)
            )
            TabLayoutMediator(
                binding.tlInfo,
                this
            ) { tab, position ->
                tab.text = infoTitle[position]
            }.attach()
        }

        binding.tlInfo.tabTextColors = context?.colorStateList(R.color.dove_gray)
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
            ibBack.setOnClickListener(onClickCallback)
            cbFavorite.setOnClickListener(onClickCallback)
        }
    }

    private val onClickCallback = View.OnClickListener { view ->
        when (view) {
            binding.ibBack -> {
                navController.navigateUp()
            }
            binding.cbFavorite -> {
                binding.apply {
                    val pokemonFavorite = PokemonFavorite(
                        name = pokemonDetail.pokemonName,
                        number = pokemonDetail.pokemonNumber,
                        image = pokemonDetail.pokemonImage,
                        type1 = pokemonDetail.pokemonType.getOrNull(0),
                        type2 = pokemonDetail.pokemonType.getOrNull(1)
                    )

                    if(cbFavorite.isChecked) {
                        viewModel.insertPokemonFavorite(pokemonFavorite)
                    } else {
                        viewModel.deletePokemonFavorite(pokemonDetail.pokemonNumber)
                    }
                }
            }
        }
    }

}