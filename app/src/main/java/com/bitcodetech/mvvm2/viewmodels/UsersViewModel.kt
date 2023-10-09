package com.bitcodetech.mvvm2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.mvvm2.models.User
import com.bitcodetech.mvvm2.models.UserPostModel
import com.bitcodetech.mvvm2.repo.UsersRepo
import kotlinx.coroutines.*

class UsersViewModel(
    private val usersRepo: UsersRepo
) : ViewModel() {

    val usersLiveData = MutableLiveData<List<User>>()
    val userPostReponseIdLiveData = MutableLiveData<Int>()

    fun getUsers() {

        CoroutineScope(Dispatchers.IO).launch {
            val users = usersRepo.getUsers()

            withContext(Dispatchers.Main) {
                usersLiveData.postValue(users)
            }
        }

    }

    fun addUser(name : String, job : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val userPostResponseModel = usersRepo.addUser(
                UserPostModel(name, job)
            )

            withContext(Dispatchers.Main) {
                userPostReponseIdLiveData.postValue(userPostResponseModel.id)
            }
        }
    }



}