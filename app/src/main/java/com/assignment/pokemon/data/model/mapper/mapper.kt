package com.assignment.pokemon.data.model.mapper

import com.assignment.pokemon.data.model.Pokemon
import com.assignment.pokemon.data.model.PokemonSpecies

fun Pokemon.mapPaging(url: String): PokemonPaging = PokemonPaging(
    pokemonUrl = url,
    pokemonName = name.orEmpty(),
    pokemonNumber = id ?: 0,
    pokemonImage = sprites?.other?.officialArtwork?.frontDefault.orEmpty(),
    pokemonType = types?.map { it.type?.name.orEmpty() }.orEmpty()
)

fun Pokemon.mapDetail(): PokemonDetail = PokemonDetail(
    pokemonName = name.orEmpty(),
    pokemonNumber = id ?: 0,
    pokemonImage = sprites?.other?.officialArtwork?.frontDefault.orEmpty(),
    pokemonType = types?.map { it.type?.name.orEmpty() }.orEmpty(),
    pokemonAbility = abilities?.map { it.ability?.name.orEmpty() }.orEmpty(),
    pokemonWeight = weight ?: 0,
    pokemonHeight = height ?: 0,
    pokemonStats = stats?.map { PokemonStat(it.stat?.name, it.baseStat) }.orEmpty()
)

fun PokemonSpecies.mapSpecies(): PokemonSpeciesDetail = PokemonSpeciesDetail(
    pokemonColor = color?.name.toString(),
    pokemonEgg = eggGroups?.map { it.name.orEmpty() }.orEmpty(),
    pokemonHatch = hatchCounter ?: 0,
    pokemonGrowth = growthRate?.name.toString(),
    pokemonHabitat = habitat?.name.toString(),
    pokemonShape = shape?.name.toString()
)

