package com.bitcodetech.mvvm2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.mvvm2.models.Place
import com.bitcodetech.mvvm2.repo.PlacesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlacesViewModel(
    val placesRepo: PlacesRepo
) : ViewModel() {

    val placesLiveData = MutableLiveData<List<Place>>()

    val apiKey = "AIzaSyDKMUzdmKS_pRfm7ACcpo5yNcNP_1fHoDM"

    fun getPlaces() {
        CoroutineScope(Dispatchers.IO).launch {
            val placesResponse = placesRepo.getPlaces(apiKey)

            withContext(Dispatchers.Main) {
                placesLiveData.postValue(placesResponse.results)
            }
        }
    }

}