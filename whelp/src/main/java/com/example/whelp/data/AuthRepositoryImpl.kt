package com.example.whelp.data

import com.example.whelp.model.AuthRequest
import com.example.whelp.model.AuthResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val apiService: ApiService) : AuthRepository {
    override suspend fun auth(body: AuthRequest): AuthResponse {
        return apiService.auth(body)
    }


}