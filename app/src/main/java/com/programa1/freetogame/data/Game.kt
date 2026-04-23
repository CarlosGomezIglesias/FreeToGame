package com.programa1.freetogame.data

import com.google.gson.annotations.SerializedName

data class Game (
    @SerializedName("id") val id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("thumbnail") var image: String,
    @SerializedName("genre") var genre: String,
    @SerializedName("short_description") var shortDescription: String,
    @SerializedName("description") var description: String?,
    @SerializedName("publisher") var publisher: String,
    @SerializedName("developer") var developer: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("platform") var platform: String,
    @SerializedName("game_url") var gameUrl: String,
    @SerializedName("minimum_system_requirements") var systemRequirements: SystemRequirements?,
    @SerializedName("screenshots") var screenshots: List<Screenshots?>

) {

}

data class SystemRequirements (
    @SerializedName("os") var os: String,
    @SerializedName("processor") var processor: String,
    @SerializedName("memory") var memory: String,
    @SerializedName("graphics") var graphics: String,
    @SerializedName("storage") var storage: String,
)

data class Screenshots(
    @SerializedName("image") var image: String
)