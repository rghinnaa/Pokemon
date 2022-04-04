package com.assignment.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("abilities")
    var abilities: List<Ability>? = listOf(),
    @SerializedName("base_experience")
    var baseExperience: Int? = 0,
    @SerializedName("forms")
    var forms: List<Form>? = listOf(),
    @SerializedName("game_indices")
    var gameIndices: List<GameIndice>? = listOf(),
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("held_items")
    var heldItems: List<Any>? = listOf(),
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("is_default")
    var isDefault: Boolean? = false,
    @SerializedName("location_area_encounters")
    var locationAreaEncounters: String? = "",
    @SerializedName("moves")
    var moves: List<Move?>? = listOf(),
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("order")
    var order: Int? = 0,
    @SerializedName("past_types")
    var pastTypes: List<Any>? = listOf(),
    @SerializedName("species")
    var species: Species? = Species(),
    @SerializedName("sprites")
    var sprites: Sprites? = Sprites(),
    @SerializedName("stats")
    var stats: List<Stat>? = listOf(),
    @SerializedName("types")
    var types: List<Type>? = listOf(),
    @SerializedName("weight")
    var weight: Int? = 0
) {
    data class Ability(
        @SerializedName("ability")
        var ability: Ability? = Ability(),
        @SerializedName("is_hidden")
        var isHidden: Boolean? = false,
        @SerializedName("slot")
        var slot: Int? = 0
    ) {
        data class Ability(
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("url")
            var url: String? = ""
        )
    }

    data class Form(
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("url")
        var url: String? = ""
    )

    data class GameIndice(
        @SerializedName("game_index")
        var gameIndex: Int? = 0,
        @SerializedName("version")
        var version: Version? = Version()
    ) {
        data class Version(
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("url")
            var url: String? = ""
        )
    }

    data class Move(
        @SerializedName("move")
        var move: Move? = Move(),
        @SerializedName("version_group_details")
        var versionGroupDetails: List<VersionGroupDetail?>? = listOf()
    ) {
        data class Move(
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("url")
            var url: String? = ""
        )

        data class VersionGroupDetail(
            @SerializedName("level_learned_at")
            var levelLearnedAt: Int? = 0,
            @SerializedName("move_learn_method")
            var moveLearnMethod: MoveLearnMethod? = MoveLearnMethod(),
            @SerializedName("version_group")
            var versionGroup: VersionGroup? = VersionGroup()
        ) {
            data class MoveLearnMethod(
                @SerializedName("name")
                var name: String? = "",
                @SerializedName("url")
                var url: String? = ""
            )

            data class VersionGroup(
                @SerializedName("name")
                var name: String? = "",
                @SerializedName("url")
                var url: String? = ""
            )
        }
    }

    data class Species(
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("url")
        var url: String? = ""
    )

    data class Sprites(
        @SerializedName("back_default")
        var backDefault: String? = "",
        @SerializedName("back_female")
        var backFemale: Any? = Any(),
        @SerializedName("back_shiny")
        var backShiny: String? = "",
        @SerializedName("back_shiny_female")
        var backShinyFemale: Any? = Any(),
        @SerializedName("front_default")
        var frontDefault: String? = "",
        @SerializedName("front_female")
        var frontFemale: Any? = Any(),
        @SerializedName("front_shiny")
        var frontShiny: String? = "",
        @SerializedName("front_shiny_female")
        var frontShinyFemale: Any? = Any(),
        @SerializedName("other")
        var other: Other? = Other(),
        @SerializedName("versions")
        var versions: Versions? = Versions()
    ) {
        data class Other(
            @SerializedName("dream_world")
            var dreamWorld: DreamWorld? = DreamWorld(),
            @SerializedName("home")
            var home: Home? = Home(),
            @SerializedName("official-artwork")
            var officialArtwork: OfficialArtwork? = OfficialArtwork()
        ) {
            data class DreamWorld(
                @SerializedName("front_default")
                var frontDefault: String? = "",
                @SerializedName("front_female")
                var frontFemale: Any? = Any()
            )

            data class Home(
                @SerializedName("front_default")
                var frontDefault: String? = "",
                @SerializedName("front_female")
                var frontFemale: Any? = Any(),
                @SerializedName("front_shiny")
                var frontShiny: String? = "",
                @SerializedName("front_shiny_female")
                var frontShinyFemale: Any? = Any()
            )

            data class OfficialArtwork(
                @SerializedName("front_default")
                var frontDefault: String? = ""
            )
        }

        data class Versions(
            @SerializedName("generation-i")
            var generationI: GenerationI? = GenerationI(),
            @SerializedName("generation-ii")
            var generationIi: GenerationIi? = GenerationIi(),
            @SerializedName("generation-iii")
            var generationIii: GenerationIii? = GenerationIii(),
            @SerializedName("generation-iv")
            var generationIv: GenerationIv? = GenerationIv(),
            @SerializedName("generation-v")
            var generationV: GenerationV? = GenerationV(),
            @SerializedName("generation-vi")
            var generationVi: GenerationVi? = GenerationVi(),
            @SerializedName("generation-vii")
            var generationVii: GenerationVii? = GenerationVii(),
            @SerializedName("generation-viii")
            var generationViii: GenerationViii? = GenerationViii()
        ) {
            data class GenerationI(
                @SerializedName("red-blue")
                var redBlue: RedBlue? = RedBlue(),
                @SerializedName("yellow")
                var yellow: Yellow? = Yellow()
            ) {
                data class RedBlue(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_gray")
                    var backGray: String? = "",
                    @SerializedName("back_transparent")
                    var backTransparent: String? = "",
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_gray")
                    var frontGray: String? = "",
                    @SerializedName("front_transparent")
                    var frontTransparent: String? = ""
                )

                data class Yellow(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_gray")
                    var backGray: String? = "",
                    @SerializedName("back_transparent")
                    var backTransparent: String? = "",
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_gray")
                    var frontGray: String? = "",
                    @SerializedName("front_transparent")
                    var frontTransparent: String? = ""
                )
            }

            data class GenerationIi(
                @SerializedName("crystal")
                var crystal: Crystal? = Crystal(),
                @SerializedName("gold")
                var gold: Gold? = Gold(),
                @SerializedName("silver")
                var silver: Silver? = Silver()
            ) {
                data class Crystal(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("back_shiny_transparent")
                    var backShinyTransparent: String? = "",
                    @SerializedName("back_transparent")
                    var backTransparent: String? = "",
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_shiny_transparent")
                    var frontShinyTransparent: String? = "",
                    @SerializedName("front_transparent")
                    var frontTransparent: String? = ""
                )

                data class Gold(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_transparent")
                    var frontTransparent: String? = ""
                )

                data class Silver(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_transparent")
                    var frontTransparent: String? = ""
                )
            }

            data class GenerationIii(
                @SerializedName("emerald")
                var emerald: Emerald? = Emerald(),
                @SerializedName("firered-leafgreen")
                var fireredLeafgreen: FireredLeafgreen? = FireredLeafgreen(),
                @SerializedName("ruby-sapphire")
                var rubySapphire: RubySapphire? = RubySapphire()
            ) {
                data class Emerald(
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    var frontShiny: String? = ""
                )

                data class FireredLeafgreen(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    var frontShiny: String? = ""
                )

                data class RubySapphire(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    var frontShiny: String? = ""
                )
            }

            data class GenerationIv(
                @SerializedName("diamond-pearl")
                var diamondPearl: DiamondPearl? = DiamondPearl(),
                @SerializedName("heartgold-soulsilver")
                var heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
                @SerializedName("platinum")
                var platinum: Platinum? = Platinum()
            ) {
                data class DiamondPearl(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_female")
                    var backFemale: Any? = Any(),
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("back_shiny_female")
                    var backShinyFemale: Any? = Any(),
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    var frontShinyFemale: Any? = Any()
                )

                data class HeartgoldSoulsilver(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_female")
                    var backFemale: Any? = Any(),
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("back_shiny_female")
                    var backShinyFemale: Any? = Any(),
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    var frontShinyFemale: Any? = Any()
                )

                data class Platinum(
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_female")
                    var backFemale: Any? = Any(),
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("back_shiny_female")
                    var backShinyFemale: Any? = Any(),
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    var frontShinyFemale: Any? = Any()
                )
            }

            data class GenerationV(
                @SerializedName("black-white")
                var blackWhite: BlackWhite? = BlackWhite()
            ) {
                data class BlackWhite(
                    @SerializedName("animated")
                    var animated: Animated? = Animated(),
                    @SerializedName("back_default")
                    var backDefault: String? = "",
                    @SerializedName("back_female")
                    var backFemale: Any? = Any(),
                    @SerializedName("back_shiny")
                    var backShiny: String? = "",
                    @SerializedName("back_shiny_female")
                    var backShinyFemale: Any? = Any(),
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    var frontShinyFemale: Any? = Any()
                ) {
                    data class Animated(
                        @SerializedName("back_default")
                        var backDefault: String? = "",
                        @SerializedName("back_female")
                        var backFemale: Any? = Any(),
                        @SerializedName("back_shiny")
                        var backShiny: String? = "",
                        @SerializedName("back_shiny_female")
                        var backShinyFemale: Any? = Any(),
                        @SerializedName("front_default")
                        var frontDefault: String? = "",
                        @SerializedName("front_female")
                        var frontFemale: Any? = Any(),
                        @SerializedName("front_shiny")
                        var frontShiny: String? = "",
                        @SerializedName("front_shiny_female")
                        var frontShinyFemale: Any? = Any()
                    )
                }
            }

            data class GenerationVi(
                @SerializedName("omegaruby-alphasapphire")
                var omegarubyAlphasapphire: OmegarubyAlphasapphire? = OmegarubyAlphasapphire(),
                @SerializedName("x-y")
                var xY: XY? = XY()
            ) {
                data class OmegarubyAlphasapphire(
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    var frontShinyFemale: Any? = Any()
                )

                data class XY(
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    var frontShinyFemale: Any? = Any()
                )
            }

            data class GenerationVii(
                @SerializedName("icons")
                var icons: Icons? = Icons(),
                @SerializedName("ultra-sun-ultra-moon")
                var ultraSunUltraMoon: UltraSunUltraMoon? = UltraSunUltraMoon()
            ) {
                data class Icons(
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any()
                )

                data class UltraSunUltraMoon(
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    var frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    var frontShinyFemale: Any? = Any()
                )
            }

            data class GenerationViii(
                @SerializedName("icons")
                var icons: Icons? = Icons()
            ) {
                data class Icons(
                    @SerializedName("front_default")
                    var frontDefault: String? = "",
                    @SerializedName("front_female")
                    var frontFemale: Any? = Any()
                )
            }
        }
    }

    data class Stat(
        @SerializedName("base_stat")
        var baseStat: Int? = 0,
        @SerializedName("effort")
        var effort: Int? = 0,
        @SerializedName("stat")
        var stat: Stat? = Stat()
    ) {
        data class Stat(
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("url")
            var url: String? = ""
        )
    }

    data class Type(
        @SerializedName("slot")
        var slot: Int? = 0,
        @SerializedName("type")
        var type: Type? = Type()
    ) {
        data class Type(
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("url")
            var url: String? = ""
        )
    }
}