package com.mastercoding.contactmanager.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
                @ColumnInfo(name = "User_id")val id:Int,
    @ColumnInfo(name = "User_name") var name:String,
    @ColumnInfo(name = "User_phnum") var phnum:String
)
