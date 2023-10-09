package com.bitcodetech.mvvm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.mvvm2.databinding.ActivityMainBinding
import com.bitcodetech.mvvm2.factory.MyViewModelFactory
import com.bitcodetech.mvvm2.network.PlacesServices
import com.bitcodetech.mvvm2.network.UserServices
import com.bitcodetech.mvvm2.repo.PlacesRepo
import com.bitcodetech.mvvm2.repo.UsersRepo
import com.bitcodetech.mvvm2.viewmodels.PlacesViewModel
import com.bitcodetech.mvvm2.viewmodels.UsersViewModel
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var placesViewModel: PlacesViewModel

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModels()
        initObservers()
        initListeners()

        binding.img.setOnClickListener {

            Glide.with(this)
                .load("https://images.pexels.com/photos/13780805/pexels-photo-13780805.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fitCenter()
                .into(binding.img)

        }

    }

    private fun initObservers() {

        usersViewModel.usersLiveData.observe(
            this
        ) {
            for(user in it) {
                Log.e("tag", user.toString())
            }

        }

        usersViewModel.userPostReponseIdLiveData.observe(
            this
        ) {
            Toast.makeText(this@MainActivity, "Id: $it", Toast.LENGTH_LONG).show()
        }


        placesViewModel.placesLiveData.observe(
            this
        ) {
            for(place in it) {
                Log.e("tag", "$place")
            }
        }

    }

    private fun initListeners() {

        binding.btnGetUsers.setOnClickListener {
            usersViewModel.getUsers()
        }

        binding.btnAddUser.setOnClickListener {
            usersViewModel.addUser("vishal", "Trainer")
        }

        binding.btnGetPlaces.setOnClickListener {
            placesViewModel.getPlaces()
        }
    }


    private fun initViewModels() {
        usersViewModel =  ViewModelProvider(
            this,
            MyViewModelFactory(
                UsersRepo(UserServices.getInstance())
            )
        ).get(UsersViewModel::class.java)

        placesViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                PlacesRepo(
                    PlacesServices.getInstance()
                )
            )
        )[PlacesViewModel::class.java]
    }
}