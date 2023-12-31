package com.mastercoding.contactmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mastercoding.contactmanager.myviewmodel.UserViewModel
import com.mastercoding.contactmanager.myviewmodel.UserviewModelFactory
import com.mastercoding.contactmanager.databinding.ActivityMainBinding
import com.mastercoding.contactmanager.room.User
import com.mastercoding.contactmanager.room.UserDataBase
import com.mastercoding.contactmanager.room.UserRepository
import com.mastercoding.contactmanager.viewUi.Myrecyclerviewadapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var userViewmodel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Room database
        val dao = UserDataBase.getInstance(application).userDao
        val repository = UserRepository(dao)
        val factory = UserviewModelFactory(repository)

        userViewmodel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.userviiewModel = userViewmodel

        binding.lifecycleOwner = this

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        userViewmodel.users.observe(this, Observer { binding.recyclerview.adapter = Myrecyclerviewadapter(it, {selectedItems : User ->listItemClicked(selectedItems)}) })


    }

    private fun listItemClicked(selectedItems: User) {
        Toast.makeText(this, "Selected name is ${selectedItems.name}", Toast.LENGTH_LONG).show()

        userViewmodel.initUpdateAndDelete(selectedItems)
    }

}