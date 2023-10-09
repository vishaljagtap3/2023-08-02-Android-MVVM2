package com.bitcodetech.mvvm2.network

import com.bitcodetech.mvvm2.models.PlacesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface PlacesServices {

    @GET("json?keyword=cruise&location=-33.8670522%2c151.1957362&radius=1500&type=restaurant")
    suspend fun getPlaces(
        @Query("key") apiKey : String
    ) : PlacesResponse

    companion object {
        var placesServices : PlacesServices? = null

        fun getInstance() : PlacesServices {

            if(placesServices == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                placesServices = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PlacesServices::class.java)
            }

            return placesServices!!
        }
    }
}