package com.edorta.rodriguez.innocv.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("id") val id: Int,// database entity
    @SerializedName("name") val name: String?,
    @SerializedName("birthdate") val birthdate: String
) : Parcelable