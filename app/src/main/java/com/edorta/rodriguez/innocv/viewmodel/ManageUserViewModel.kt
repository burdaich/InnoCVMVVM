package com.edorta.rodriguez.innocv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edorta.rodriguez.innocv.domain.SaveUserUsesCase
import com.edorta.rodriguez.innocv.domain.UpdateUserUsesCase
import com.edorta.rodriguez.innocv.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageUserViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUsesCase,
    private val saveUserUsesCase: SaveUserUsesCase
) :
    ViewModel() {

    private val _updatedUserResponse = MutableLiveData<UserModel?>()
    val updatedUserResponse: LiveData<UserModel?> get() = _updatedUserResponse

    private val _saveUserResponse = MutableLiveData<UserModel?>()
    val saveUserResponse: LiveData<UserModel?> get() = _saveUserResponse


    fun updateUser(userModel: UserModel) {
        viewModelScope.launch {
            val result = updateUserUseCase(userModel)
            _updatedUserResponse.postValue(if (result) userModel else null)
        }


        fun saveUser(userModel: UserModel) {
            viewModelScope.launch {
                val result = saveUserUsesCase(userModel)
                _saveUserResponse.postValue(if (result) userModel else null)
            }
        }

    }
}