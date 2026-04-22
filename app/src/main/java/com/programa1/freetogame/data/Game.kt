package com.programa1.freetogame.data

import com.google.gson.annotations.SerializedName

data class Game (
    @SerializedName("id") val id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("thumbnail") var image: String,
    @SerializedName("genre") var genre: String,
    @SerializedName("short_description") var short_description: String,
    @SerializedName("publisher") var publisher: String,
    @SerializedName("developer") var developer: String,
    @SerializedName("release_date") var release_date: String,
    @SerializedName("platform") var platform: String

) {

}