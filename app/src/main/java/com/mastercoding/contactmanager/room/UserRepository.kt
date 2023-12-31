package com.mastercoding.contactmanager.room

class UserRepository(private val dao: UserDAO) {

    val users = dao.getAlluserInDB()

    suspend fun insert(user : User):Long{
        return dao.insertuser(user)
    }

    suspend fun delete(user : User){
        return dao.deleteuser(user)
    }
    suspend fun update(user:User)
    {
        return dao.updateuser(user)
    }
    suspend fun deleteall()
    {
        return dao.deleteAll()
    }





}