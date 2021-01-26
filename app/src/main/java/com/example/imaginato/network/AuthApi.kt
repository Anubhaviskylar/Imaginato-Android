package com.example.imaginato.network

import com.example.imaginato.models.LoginRequest
import com.example.imaginato.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : LoginResponse
}