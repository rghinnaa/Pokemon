package com.assignment.pokemon.viewmodel

import androidx.lifecycle.*
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.data.local.entity.PokemonList
import com.assignment.pokemon.data.model.mapper.PokemonDetail
import com.assignment.pokemon.data.model.mapper.PokemonSpeciesDetail
import com.assignment.pokemon.data.remote.Result
import com.assignment.pokemon.data.repository.MainRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(mainRepository: MainRepository) : ViewModel() {
    private val repository = mainRepository

    private var _pokemon: MutableLiveData<Result<PokemonDetail>> = MutableLiveData()
    val pokemon: LiveData<Result<PokemonDetail>> get() = _pokemon
    private var _pokemonSpecies: MutableLiveData<Result<PokemonSpeciesDetail>> = MutableLiveData()
    val pokemonSpecies: LiveData<Result<PokemonSpeciesDetail>> get() = _pokemonSpecies
    private var _pokemonFavorite: MutableLiveData<List<PokemonFavorite>> = MutableLiveData()
    val pokemonFavorite: LiveData<List<PokemonFavorite>> get() = _pokemonFavorite
    private var _searchPokemon: MutableLiveData<List<PokemonList>> = MutableLiveData()
    val searchPokemon: LiveData<List<PokemonList>> get() = _searchPokemon

    fun requestPokemonList() = repository.requestPokemonList().asLiveData()

    fun requestPokemon(pokemonId: Int) = repository.requestPokemon(pokemonId)
        .onEach { result ->
            _pokemon.value = result

            requestPokemonSpecies(pokemonId)
        }.launchIn(viewModelScope)

    private fun requestPokemonSpecies(pokemonId: Int) = repository.requestPokemonSpecies(pokemonId)
        .onEach { result ->
            _pokemonSpecies.value = result
        }.launchIn(viewModelScope)

    fun getPokemonFavorite() = repository.getFavorite()
        .onEach { result ->
            _pokemonFavorite.value = result
        }.launchIn(viewModelScope)

    fun insertPokemonFavorite(pokemonFavorite: PokemonFavorite) = viewModelScope.launch {
        repository.insertFavorite(pokemonFavorite)
    }

    fun deletePokemonFavorite(number: Int?) = viewModelScope.launch {
        repository.deleteFavorite(number)
    }

    fun searchPokemon(query: String) = repository.searchPokemonList(query)
        .onEach { result ->
        _searchPokemon.value = result
    }.launchIn(viewModelScope)

}