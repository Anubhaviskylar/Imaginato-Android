package com.example.imaginato.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imaginato.models.LoginResponse
import com.example.imaginato.network.Resource
import com.example.imaginato.repository.AuthRepository
import kotlinx.coroutines.launch

class LogInViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse : LiveData<Resource<LoginResponse>> get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(email, password)
    }
}