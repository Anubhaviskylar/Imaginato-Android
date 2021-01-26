package com.example.imaginato.repository

import com.example.imaginato.models.LoginRequest
import com.example.imaginato.network.AuthApi

class AuthRepository(
    private val api: AuthApi
): BaseRepository() {

    suspend fun login(
        userName:String,
        password: String
    ) = safeApiCall {
        val loginRequest = LoginRequest(userName, password)
        api.loginUser(loginRequest)
    }

}