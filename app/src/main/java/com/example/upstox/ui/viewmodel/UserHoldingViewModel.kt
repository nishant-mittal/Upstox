package com.example.upstox.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upstox.network.api.UserHoldingApi
import com.example.upstox.network.response.UserHoldingResponseDto
import com.example.upstox.ui.models.UiState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserHoldingViewModel : ViewModel() {

    private val _userHoldingData = MutableLiveData<UiState<UserHoldingResponseDto?>>()
    val userHoldingData: LiveData<UiState<UserHoldingResponseDto?>> = _userHoldingData

    init {
        getUserHoldings()
    }

    private fun getUserHoldings() {
        viewModelScope.launch(IO) {
            try {
                _userHoldingData.postValue(UiState.Loading)
                val response = UserHoldingApi.create().getUserHolding()
                if (response.isSuccessful) {
                    _userHoldingData.postValue(UiState.Success(data = response.body()?.data))
                } else {
                    _userHoldingData.postValue(UiState.Error(errorText = "Could not load data"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _userHoldingData.postValue(UiState.Error(errorText = "Something went wrong"))
            }
        }
    }
}