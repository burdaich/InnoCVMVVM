package com.edorta.rodriguez.innocv.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: Int,// database entity
    @SerializedName("name") val name: String?,
    @SerializedName("birthdate") val birthdate: String
)