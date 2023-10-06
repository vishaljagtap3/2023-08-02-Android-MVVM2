package com.bitcodetech.mvvm2.repo

import com.bitcodetech.mvvm2.models.User
import com.bitcodetech.mvvm2.network.UserServices

class UsersRepo(
    val userServices: UserServices
) {

    suspend fun getUsers() : List<User> {

        val users = userServices.getUsers().users

        /*val users = ArrayList<User>()
        users.add(User(101, "a@a.com", "AA", "AAA", null))
        users.add(User(101, "a@a.com", "AA", "AAA", null))*/

        return users!!
    }

}