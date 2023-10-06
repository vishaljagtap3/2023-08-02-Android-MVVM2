package com.bitcodetech.mvvm2.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.mvvm2.repo.UsersRepo
import com.bitcodetech.mvvm2.viewmodels.UsersViewModel

class MyViewModelFactory(
    val usersRepo : UsersRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(usersRepo) as T
    }
}