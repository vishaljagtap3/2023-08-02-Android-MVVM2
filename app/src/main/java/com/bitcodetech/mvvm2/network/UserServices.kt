package com.bitcodetech.mvvm2.network

import android.util.Log
import com.bitcodetech.mvvm2.models.User
import com.bitcodetech.mvvm2.models.UserPostModel
import com.bitcodetech.mvvm2.models.UserPostResponseModel
import com.bitcodetech.mvvm2.models.UsersResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserServices {

    @GET("users?page=2")
    suspend fun getUsers() : UsersResponse

    @GET("users?page={pageNo}")
    suspend fun getUsers(
        @Path("pageNo") pageNo : Int
    ) : UsersResponse

    @POST("users")
    suspend fun addUser(
        @Body user : UserPostModel
    ) : UserPostResponseModel

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