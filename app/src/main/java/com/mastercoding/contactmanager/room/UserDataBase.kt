package com.mastercoding.contactmanager.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [User::class], version = 1)
abstract class UserDataBase :RoomDatabase(){
    abstract val userDao : UserDAO

    //Singleton
//    Design pattern that creates only one object at runtime of an database class
    //reduces the connectivity, memory leaks, Time, connnection time etc, as companion object

    companion object{
        @Volatile
        private var INSTANCE : UserDataBase ?=null
        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context : Context): UserDataBase{
           synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    //Creating the  Database object
                    instance = Room.databaseBuilder(context.applicationContext, UserDataBase::class.java, "user_db").build()
                }
                return instance
            }
        }

    }




}