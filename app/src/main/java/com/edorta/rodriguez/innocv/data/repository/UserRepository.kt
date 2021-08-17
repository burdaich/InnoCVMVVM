package com.edorta.rodriguez.innocv.data.repository

import com.edorta.rodriguez.innocv.data.network.UserService
import com.edorta.rodriguez.innocv.model.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserService) {

    suspend fun getAllUsers(): List<UserModel> {
        return api.getAllUsers()
    }

    suspend fun deleteUser(userModel: UserModel): Boolean {
        return api.deleteUser(userModel)
    }

    suspend fun updateUser(userModel: UserModel): Boolean {
        return api.updateUser(userModel)

    }

    suspend fun saveUser(userModel: UserModel): Boolean {
        return api.saveUser(userModel)
    }
}