package com.example.whelp.data

import com.example.whelp.model.AuthResponse
import com.example.whelp.model.UserCredentials
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val apiService: ApiService) : AuthRepository {
    override suspend fun auth(body: UserCredentials): AuthResponse {
        return apiService.auth(body)
    }

}