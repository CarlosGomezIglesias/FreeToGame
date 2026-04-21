package com.programa1.freetogame.data

import com.google.gson.annotations.SerializedName

data class Game (
    @SerializedName("id") val id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("thumbnail") var image: String
) {

}