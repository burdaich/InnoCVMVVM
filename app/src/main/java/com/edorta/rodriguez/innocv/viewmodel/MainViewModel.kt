package com.edorta.rodriguez.innocv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edorta.rodriguez.innocv.domain.DeleteUserUsesCase
import com.edorta.rodriguez.innocv.domain.GetUsersUseCase
import com.edorta.rodriguez.innocv.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUsersUseCase,
    private val deleteUserUsesCase: DeleteUserUsesCase
) : ViewModel() {
    private val _userModelResponse = MutableLiveData<List<UserModel>>()
    val userModelResponse: LiveData<List<UserModel>> get() = _userModelResponse

    private val _deleteUserResponse = MutableLiveData<UserModel?>()
    val deleteUserResponse: LiveData<UserModel?> get() = _deleteUserResponse


    fun getUsers() {
        viewModelScope.launch {
            val result = getUserUseCase()
            result.let {
                _userModelResponse.postValue(it)
            }
        }
    }

    fun deleteUser(userModel: UserModel) {
        viewModelScope.launch {
            val result = deleteUserUsesCase(userModel)
            _deleteUserResponse.postValue(if (result) userModel else null)
        }
    }
}
