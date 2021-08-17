package com.edorta.rodriguez.innocv.ui.adapter

import com.edorta.rodriguez.innocv.model.UserModel

interface UserAdapterListener {

    fun onUserAdapterClick(userModel: UserModel)
    abstract fun onUserDeleteClick(userModel: UserModel)
}