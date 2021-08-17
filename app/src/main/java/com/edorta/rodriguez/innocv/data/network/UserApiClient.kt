package com.edorta.rodriguez.innocv.data.network

import com.edorta.rodriguez.innocv.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface UserApiClient {
    @GET("User")
    suspend fun getAllUsers(): Response<List<UserModel>>

    @DELETE("User/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<ResponseBody>

    @PUT("User")
    suspend fun updateUser(@Body userModel: UserModel): Response<ResponseBody>

    @POST("User")
    suspend fun saveUser(@Body userModel: UserModel): Response<ResponseBody>
}