package com.edorta.rodriguez.innocv.data.network

import com.edorta.rodriguez.innocv.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {
    @GET("User")
    suspend fun getAllUsers(): Response<List<UserModel>>
}