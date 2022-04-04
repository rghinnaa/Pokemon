package com.assignment.pokemon.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.assignment.pokemon.R
import com.assignment.pokemon.data.model.mapper.PokemonDetail
import com.assignment.pokemon.data.model.mapper.PokemonSpeciesDetail
import com.assignment.pokemon.databinding.FragmentBaseInfoBinding
import com.assignment.pokemon.utils.capital
import com.assignment.pokemon.utils.viewBinding
import com.google.gson.Gson

class BaseInfoFragment : Fragment(R.layout.fragment_base_info) {

    private val binding by viewBinding<FragmentBaseInfoBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {
        setDetail()
        setSpecies()
    }

    private fun setDetail() {
        val detail = arguments?.getString(POKEMON_DETAIL).orEmpty()
        val item = Gson().fromJson(detail, PokemonDetail::class.java)

        binding.apply {
            val height = item.pokemonHeight.toDouble()
            val weight = item.pokemonWeight.toDouble()

            tvHeight.text = context?.getString(R.string.heightCm, (height.times(10.0)).toString())
            tvWeight.text = context?.getString(R.string.weightKg, (weight.div(10.0)).toString())

            if (item.pokemonAbility.size > 1) {
                val data = item.pokemonAbility.map { name ->
                    name.capital()
                }
                binding.tvAbilities.text = data.joinToString(", ")
            } else
                binding.tvAbilities.text = item.pokemonAbility.firstOrNull()?.capital()
        }
    }

    private fun setSpecies() {
        val species = arguments?.getString(POKEMON_SPECIES).orEmpty()
        val item = Gson().fromJson(species, PokemonSpeciesDetail::class.java)

        binding.apply {
            tvColor.text = item.pokemonColor.capital()
            tvEggCycle.text = item.pokemonHatch.toString()
            tvHabitat.text = item.pokemonHabitat.capital()
            if (item.pokemonEgg.size > 1) {
                val data = item.pokemonEgg.map { name ->
                    name.capital()
                }
                binding.tvEggGroup.text = data.joinToString(", ")
            } else
                binding.tvEggGroup.text = item.pokemonEgg.firstOrNull()?.capital()
        }
    }

    companion object {
        private const val POKEMON_DETAIL = "detail"
        private const val POKEMON_SPECIES = "species"

        fun getInstance(detail: String, species: String) =
            BaseInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(POKEMON_DETAIL, detail)
                    putString(POKEMON_SPECIES, species)
                }
            }
    }

}