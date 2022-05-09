package com.example.whelp.data

import com.example.whelp.model.AuthResponse
import com.example.whelp.model.UserCredentials
import retrofit2.http.Body

interface AuthRepository {
    suspend fun auth(@Body body: UserCredentials): AuthResponse
}