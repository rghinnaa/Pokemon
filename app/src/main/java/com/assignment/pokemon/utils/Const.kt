package com.assignment.pokemon.utils

object Const {

    const val BASE_URL = "https://pokeapi.co/api/v2/"

    const val POKEMON_LIST = "pokemon"
    const val POKEMON = "pokemon/{id}"
    const val POKEMON_SPECIES = "pokemon-species/{id}"

    const val emptyString = ""
    const val emptyInt = -1

    object PokemonStat {
        const val HP = "hp"
        const val ATTACK = "attack"
        const val DEFENSE = "defense"
        const val SPECIAL_ATTACK = "special-attack"
        const val SPECIAL_DEFENSE = "special-defense"
        const val SPEED = "speed"
    }

    object Database {
        const val POKEMON_DATABASE = "pokemon_database"

        object Table {
            const val POKEMON_FAVORITE = "pokemon_favorite_entity"
            const val POKEMON_LIST = "pokemon_list_entity"
            const val POKEMON_LIST_KEY = "pokemon_list_key_entity"
        }
    }

}