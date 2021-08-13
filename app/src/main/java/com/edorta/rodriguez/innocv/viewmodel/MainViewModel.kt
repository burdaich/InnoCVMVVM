package com.edorta.rodriguez.innocv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edorta.rodriguez.innocv.domain.GetUsersUseCase
import com.edorta.rodriguez.innocv.model.UserModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _userModelResponse = MutableLiveData<List<UserModel>>()
    val userModelResponse: LiveData<List<UserModel>> get() = _userModelResponse

    var getUserUseCase = GetUsersUseCase()


    fun getUsers() {
        viewModelScope.launch {
            val result = getUserUseCase()
            result.let {
                _userModelResponse.postValue(it)
            }
        }
    }
}
