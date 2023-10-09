package com.bitcodetech.mvvm2.repo

import com.bitcodetech.mvvm2.models.PlacesResponse
import com.bitcodetech.mvvm2.network.PlacesServices

class PlacesRepo(
    val placesServices: PlacesServices
) : Repo {

    suspend fun getPlaces(apiKey : String) : PlacesResponse {
        return placesServices.getPlaces(apiKey)
    }
}