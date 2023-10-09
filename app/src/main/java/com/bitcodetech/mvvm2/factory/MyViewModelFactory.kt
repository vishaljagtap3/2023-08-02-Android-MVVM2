package com.bitcodetech.mvvm2.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.mvvm2.repo.PlacesRepo
import com.bitcodetech.mvvm2.repo.Repo
import com.bitcodetech.mvvm2.repo.UsersRepo
import com.bitcodetech.mvvm2.viewmodels.PlacesViewModel
import com.bitcodetech.mvvm2.viewmodels.UsersViewModel

class MyViewModelFactory(
    val repo : Repo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UsersViewModel::class.java) && repo is UsersRepo) {
            return UsersViewModel(repo) as T
        }

        if(modelClass.isAssignableFrom(PlacesViewModel::class.java) && repo is PlacesRepo) {
            return PlacesViewModel(repo) as T
        }

        throw java.lang.Error("Unable to create view model")
    }
}