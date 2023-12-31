package com.mastercoding.contactmanager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    @Insert
    suspend fun insertuser(user : User):Long

    @Update
    suspend fun updateuser(user: User)

    @Delete
    suspend fun deleteuser(user: User)


    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user")
    fun getAlluserInDB() : LiveData<List<User>>





}