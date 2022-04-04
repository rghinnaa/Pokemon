package com.assignment.pokemon.ui.main.adapter

import androidx.lifecycle.Lifecycle
import com.assignment.pokemon.base.BaseAdapter
import com.assignment.pokemon.base.BasePagingAdapter
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.data.local.entity.PokemonList
import com.assignment.pokemon.data.model.mapper.PokemonPaging
import com.assignment.pokemon.databinding.ItemPokemonBinding
import com.assignment.pokemon.ui.main.viewholder.FavoriteViewHolder
import com.assignment.pokemon.ui.main.viewholder.PokemonViewHolder
import com.assignment.pokemon.ui.main.viewholder.SearchViewHolder

object MainAdapter {

    var numberFav = emptyList<Int>()

    fun pokemonAdapter(lifecycle: Lifecycle): BasePagingAdapter<PokemonPaging, ItemPokemonBinding> {
        return BasePagingAdapter.simplePagingAdapterOf(
            register = BasePagingAdapter.Register(
                onBindHolder = { _, item, binding ->
                    binding.run {
                        val holder = PokemonViewHolder(binding)

                        holder.setNumberFavorite(numberFav)
                        holder.bind(item)
                        holder.setOnFavoriteListener { item, active ->
                            onClickFavorite.invoke(item, active)
                        }
                    }
                }
            ),
            diff = BasePagingAdapter.Diff(
                areItemsTheSame = { old, new -> old.pokemonNumber == new.pokemonNumber },
                areContentsTheSame = { old, new -> old == new }
            ),
            lifecycle = lifecycle
        )
    }

    val favoriteAdapter =
        BaseAdapter.adapterOf<PokemonFavorite, ItemPokemonBinding>(
            register = BaseAdapter.Register(
                onBindHolder = { _, item, binding ->
                    binding.run {
                        val holder = FavoriteViewHolder(binding)

                        holder.bind(item)
                        holder.setOnFavoriteListener { item, isChecked ->
                            onClickButtonFavorite.invoke(item, isChecked)
                        }
                    }
                }
            ),
            diff = BaseAdapter.Diff(
                areItemsTheSame = { old, new -> old == new },
                areContentsTheSame = { old, new -> old == new }
            )
        )

    val searchAdapter =
        BaseAdapter.adapterOf<PokemonList, ItemPokemonBinding>(
            register = BaseAdapter.Register(
                onBindHolder = { _, item, binding ->
                    binding.run {
                        val holder = SearchViewHolder(binding)

                        holder.setNumberFavorite(numberFav)
                        holder.bind(item)
                        holder.setOnFavoriteListener { item, isChecked ->
                            onClickSearchFavorite.invoke(item, isChecked)
                        }
                    }
                }
            ),
            diff = BaseAdapter.Diff(
                areItemsTheSame = { old, new -> old == new },
                areContentsTheSame = { old, new -> old == new }
            )
        )

    fun setNumberFavorite(number: List<Int>) {
        numberFav = number
    }

    private var onClickFavorite: (PokemonPaging, Boolean) -> Unit = { _, _ -> }

    fun setOnFavoriteListener(callback: (item: PokemonPaging, active: Boolean) -> Unit) {
        onClickFavorite = callback
    }

    private var onClickButtonFavorite: (PokemonFavorite, Boolean) -> Unit = { _, _ -> }

    fun setOnButtonFavoriteListener(callback: (item: PokemonFavorite, active: Boolean) -> Unit) {
        onClickButtonFavorite = callback
    }

    private var onClickSearchFavorite: (PokemonList, Boolean) -> Unit = { _, _ -> }

    fun setOnSearchFavoriteListener(callback: (item: PokemonList, active: Boolean) -> Unit) {
        onClickSearchFavorite = callback
    }

}