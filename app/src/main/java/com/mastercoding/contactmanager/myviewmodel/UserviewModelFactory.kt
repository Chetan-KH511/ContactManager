package com.mastercoding.contactmanager.myviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mastercoding.contactmanager.room.UserRepository
import java.lang.IllegalArgumentException

class UserviewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}