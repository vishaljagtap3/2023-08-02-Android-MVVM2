package com.bitcodetech.mvvm2.network

import android.util.Log
import com.bitcodetech.mvvm2.models.User
import com.bitcodetech.mvvm2.models.UsersResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserServices {

    @GET("users?page=2")
    suspend fun getUsers() : UsersResponse

    companion object {

        var userServices: UserServices? = null

        fun getInstance() : UserServices {

            if(userServices == null) {
                userServices =
                    Retrofit.Builder()
                        .baseUrl("https://reqres.in/api/")
                        .addConverterFactory(
                            GsonConverterFactory.create()
                        ).build().create(UserServices::class.java)

                Log.e("tag", userServices!!::class.java.name)
            }
            return userServices!!
        }

    }
}