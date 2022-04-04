package com.assignment.pokemon.data.model


import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("base_happiness")
    var baseHappiness: Int? = null,
    @SerializedName("capture_rate")
    var captureRate: Int? = null,
    @SerializedName("color")
    var color: Color? = null,
    @SerializedName("egg_groups")
    var eggGroups: List<EggGroup>? = null,
    @SerializedName("evolution_chain")
    var evolutionChain: EvolutionChain? = null,
    @SerializedName("evolves_from_species")
    var evolvesFromSpecies: Any? = null,
    @SerializedName("flavor_text_entries")
    var flavorTextEntries: List<FlavorTextEntry>? = null,
    @SerializedName("form_descriptions")
    var formDescriptions: List<Any>? = null,
    @SerializedName("forms_switchable")
    var formsSwitchable: Boolean? = null,
    @SerializedName("gender_rate")
    var genderRate: Int? = null,
    @SerializedName("genera")
    var genera: List<Genera?>? = null,
    @SerializedName("generation")
    var generation: Generation? = null,
    @SerializedName("growth_rate")
    var growthRate: GrowthRate? = null,
    @SerializedName("habitat")
    var habitat: Habitat? = null,
    @SerializedName("has_gender_differences")
    var hasGenderDifferences: Boolean? = null,
    @SerializedName("hatch_counter")
    var hatchCounter: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("is_baby")
    var isBaby: Boolean? = null,
    @SerializedName("is_legendary")
    var isLegendary: Boolean? = null,
    @SerializedName("is_mythical")
    var isMythical: Boolean? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("names")
    var names: List<Name>? = null,
    @SerializedName("order")
    var order: Int? = null,
    @SerializedName("pal_park_encounters")
    var palParkEncounters: List<PalParkEncounter>? = null,
    @SerializedName("pokedex_numbers")
    var pokedexNumbers: List<PokedexNumber>? = null,
    @SerializedName("shape")
    var shape: Shape? = null,
    @SerializedName("varieties")
    var varieties: List<Variety>? = null
) {
    data class Color(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("url")
        var url: String? = null
    )

    data class EggGroup(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("url")
        var url: String? = null
    )

    data class EvolutionChain(
        @SerializedName("url")
        var url: String? = null
    )

    data class FlavorTextEntry(
        @SerializedName("flavor_text")
        var flavorText: String? = null,
        @SerializedName("language")
        var language: Language? = null,
        @SerializedName("version")
        var version: Version? = null
    ) {
        data class Language(
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("url")
            var url: String? = null
        )

        data class Version(
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("url")
            var url: String? = null
        )
    }

    data class Genera(
        @SerializedName("genus")
        var genus: String? = null,
        @SerializedName("language")
        var language: Language? = null
    ) {
        data class Language(
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("url")
            var url: String? = null
        )
    }

    data class Generation(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("url")
        var url: String? = null
    )

    data class GrowthRate(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("url")
        var url: String? = null
    )

    data class Habitat(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("url")
        var url: String? = null
    )

    data class Name(
        @SerializedName("language")
        var language: Language? = null,
        @SerializedName("name")
        var name: String? = null
    ) {
        data class Language(
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("url")
            var url: String? = null
        )
    }

    data class PalParkEncounter(
        @SerializedName("area")
        var area: Area? = null,
        @SerializedName("base_score")
        var baseScore: Int? = null,
        @SerializedName("rate")
        var rate: Int? = null
    ) {
        data class Area(
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("url")
            var url: String? = null
        )
    }

    data class PokedexNumber(
        @SerializedName("entry_number")
        var entryNumber: Int? = null,
        @SerializedName("pokedex")
        var pokedex: Pokedex? = null
    ) {
        data class Pokedex(
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("url")
            var url: String? = null
        )
    }

    data class Shape(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("url")
        var url: String? = null
    )

    data class Variety(
        @SerializedName("is_default")
        var isDefault: Boolean? = null,
        @SerializedName("pokemon")
        var pokemon: Pokemon? = null
    ) {
        data class Pokemon(
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("url")
            var url: String? = null
        )
    }
}