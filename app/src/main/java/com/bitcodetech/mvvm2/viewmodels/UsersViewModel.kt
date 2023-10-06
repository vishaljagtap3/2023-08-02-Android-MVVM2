package com.bitcodetech.mvvm2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.mvvm2.models.User
import com.bitcodetech.mvvm2.repo.UsersRepo
import kotlinx.coroutines.*

class UsersViewModel(
    private val usersRepo: UsersRepo
) : ViewModel() {

    val usersLiveData = MutableLiveData<List<User>>()

    fun getUsers() {

        CoroutineScope(Dispatchers.IO).launch {
            val users = usersRepo.getUsers()

            withContext(Dispatchers.Main) {
                usersLiveData.postValue(users)
            }
        }

    }

}