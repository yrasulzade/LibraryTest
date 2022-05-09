package com.example.whelp.data

import com.example.whelp.model.AuthRequest
import com.example.whelp.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth")
    suspend fun auth(@Body auth: AuthRequest) : AuthResponse

}