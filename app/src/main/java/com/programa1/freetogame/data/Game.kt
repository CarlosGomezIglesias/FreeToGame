package com.programa1.freetogame.data

import com.google.gson.annotations.SerializedName

data class Game (
    @SerializedName("id") val id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("thumbnail") var image: String,
    @SerializedName("genre") var genre: String,
    @SerializedName("short_description") var shortDescription: String,
    @SerializedName("description") var description: String,
    @SerializedName("publisher") var publisher: String,
    @SerializedName("developer") var developer: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("platform") var platform: String,
    @SerializedName("game_url") var gameUrl: String,

) {

}