package com.bitcodetech.mvvm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.mvvm2.databinding.ActivityMainBinding
import com.bitcodetech.mvvm2.factory.MyViewModelFactory
import com.bitcodetech.mvvm2.network.UserServices
import com.bitcodetech.mvvm2.repo.UsersRepo
import com.bitcodetech.mvvm2.viewmodels.UsersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModels()
        initListeners()
        initObservers()


    }

    private fun initObservers() {
        usersViewModel.usersLiveData.observe(
            this
        ) {
            for(user in it) {
                Log.e("tag", user.toString())
            }

        }
    }

    private fun initListeners() {

        binding.btnGetUsers.setOnClickListener {
            usersViewModel.getUsers()
        }
    }


    private fun initViewModels() {
        usersViewModel =  ViewModelProvider(
            this,
            MyViewModelFactory(
                UsersRepo(UserServices.getInstance())
            )
        ).get(UsersViewModel::class.java)
    }
}