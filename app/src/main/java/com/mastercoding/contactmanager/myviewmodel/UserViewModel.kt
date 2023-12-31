package com.mastercoding.contactmanager.myviewmodel
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.mastercoding.contactmanager.room.UserRepository
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mastercoding.contactmanager.room.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel(), Observable {

    val users = repository.users
    private var isUpdateorDelete = false
    private lateinit var userToUpdateorDelete : User

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputPhone = MutableLiveData<String?>()

    @Bindable
    val saveorUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearallordeleteButtontext = MutableLiveData<String>()

    init {
        saveorUpdateButtonText.value = "Save"
        clearallordeleteButtontext.value = "Clear all"
    }

    fun Saveorupdate(){
        if(isUpdateorDelete){
            //Make Update
            userToUpdateorDelete.name = inputName.value!!
            userToUpdateorDelete.phnum = inputPhone.value!!
            update(userToUpdateorDelete)



        }else{
            //insert functionality
            val name =  inputName.value!!
            val phnum = inputPhone.value!!

            insert(User(0, name, phnum))

            inputName.value = null
            inputPhone.value = null
        }

    }
    fun Clearallordelete(){
        if(isUpdateorDelete){
            delete(userToUpdateorDelete)
        }else{
            clearAll()
        }
        clearAll()
    }
    fun insert(user:User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun clearAll()=viewModelScope.launch {
        repository.deleteall()
    }

    fun update(user:User) = viewModelScope.launch {
        repository.update(user)

        //Resetting the buttons and feelds
        inputName.value = null
        inputPhone.value = null
        isUpdateorDelete = false
        saveorUpdateButtonText.value = "Save"
        clearallordeleteButtontext.value = "Clear all"
    }

    fun delete(user:User) = viewModelScope.launch {
        repository.delete(user)

        //Resetting the buttons and feelds
        inputName.value = null
        inputPhone.value = null
        isUpdateorDelete = false
        saveorUpdateButtonText.value = "Save"
        clearallordeleteButtontext.value = "Clear all"
    }

    fun initUpdateAndDelete(user : User){

        inputName.value = user.name
        inputPhone.value = user.phnum
        isUpdateorDelete = true
        userToUpdateorDelete = user
        saveorUpdateButtonText.value = "Update"
        clearallordeleteButtontext.value = "Delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}