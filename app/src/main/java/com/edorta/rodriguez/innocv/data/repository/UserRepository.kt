package com.edorta.rodriguez.innocv.data.repository

import com.edorta.rodriguez.innocv.data.network.UserService
import com.edorta.rodriguez.innocv.model.UserModel

class UserRepository {

    private val api = UserService()
    suspend fun getAllUsers(): List<UserModel> {
        val response = api.getQuotes()
        return response
    }
}