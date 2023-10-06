package com.bitcodetech.mvvm2.models

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    val page : Int,
    @SerializedName("data")
    val users : List<User>
)
