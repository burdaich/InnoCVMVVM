package com.edorta.rodriguez.innocv.data.network

import com.edorta.rodriguez.innocv.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val api: UserApiClient) {

    suspend fun getAllUsers(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllUsers()
            response.body() ?: emptyList()
        }
    }

    suspend fun deleteUser(userModel: UserModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = api.deleteUser(userModel.id)
            response.code() == 200
        }
    }

    suspend fun updateUser(userModel: UserModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = api.updateUser(userModel)
            response.code() == 200
        }
    }

    suspend fun saveUser(userModel: UserModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = api.saveUser(userModel)
            response.code() == 200
        }
    }

}