package com.example.whelp.data

import com.example.whelp.model.AuthRequest
import com.example.whelp.model.AuthResponse
import retrofit2.http.Body

interface AuthRepository {
    suspend fun auth(@Body body: AuthRequest): AuthResponse

}