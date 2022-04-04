package com.assignment.pokemon.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.assignment.pokemon.R
import com.assignment.pokemon.data.model.mapper.PokemonDetail
import com.assignment.pokemon.databinding.FragmentBaseStatBinding
import com.assignment.pokemon.utils.Const.PokemonStat.ATTACK
import com.assignment.pokemon.utils.Const.PokemonStat.DEFENSE
import com.assignment.pokemon.utils.Const.PokemonStat.HP
import com.assignment.pokemon.utils.Const.PokemonStat.SPECIAL_ATTACK
import com.assignment.pokemon.utils.Const.PokemonStat.SPECIAL_DEFENSE
import com.assignment.pokemon.utils.Const.PokemonStat.SPEED
import com.assignment.pokemon.utils.viewBinding
import com.google.gson.Gson

class BaseStatFragment: Fragment(R.layout.fragment_base_stat) {

    private val binding by viewBinding<FragmentBaseStatBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDetail()

    }

    private fun setDetail() {
        val detail = arguments?.getString(POKEMON_DETAIL).orEmpty()
        val item = Gson().fromJson(detail, PokemonDetail::class.java).pokemonStats

        binding.apply {
            item.map { stat ->
                val baseStat = stat.baseStat ?: 0

                when (stat.name) {
                    HP -> {
                        pgHP.progress = baseStat
                        tvHpNumber.text = baseStat.toString()
                    }
                    ATTACK -> {
                        pgAttack.progress = baseStat
                        tvAttackNumber.text = baseStat.toString()
                    }
                    DEFENSE -> {
                        pgDefense.progress = baseStat
                        tvDefenseNumber.text = baseStat.toString()
                    }
                    SPECIAL_ATTACK -> {
                        pgSpecialAttack.progress = baseStat
                        tvSpecialAttackNumber.text = baseStat.toString()
                    }
                    SPECIAL_DEFENSE -> {
                        pgSpecialDefense.progress = baseStat
                        tvSpecialDefenseNumber.text = baseStat.toString()
                    }
                    SPEED -> {
                        pgSpeed.progress = baseStat
                        tvSpeedNumber.text = baseStat.toString()
                    }
                }
            }
        }
    }

    companion object {
        private const val POKEMON_DETAIL = "detail"

        fun getInstance(detail: String) =
            BaseStatFragment().apply {
                arguments = Bundle().apply {
                    putString(POKEMON_DETAIL, detail)
                }
            }
    }

}