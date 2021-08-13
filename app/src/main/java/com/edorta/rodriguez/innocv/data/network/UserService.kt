package com.edorta.rodriguez.innocv.data.network

import com.edorta.rodriguez.innocv.core.RetrofitHelper
import com.edorta.rodriguez.innocv.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(UserApiClient::class.java).getAllUsers()
            response.body() ?: emptyList()
        }
    }


}