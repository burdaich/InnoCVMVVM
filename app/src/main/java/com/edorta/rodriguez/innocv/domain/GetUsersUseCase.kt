package com.edorta.rodriguez.innocv.domain

import com.edorta.rodriguez.innocv.data.repository.UserRepository
import com.edorta.rodriguez.innocv.model.UserModel
import javax.inject.Inject


class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(): List<UserModel>? = repository.getAllUsers()
}