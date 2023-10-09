package com.bitcodetech.mvvm2.models

import com.google.gson.annotations.SerializedName

data class Place(
    val name : String,
    @SerializedName("vicinity")
    val address : String,
    val rating : Double
)

data class PlacesResponse(
    val status : String,
    val results : List<Place>
)
