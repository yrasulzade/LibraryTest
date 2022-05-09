package com.example.whelp.data

import com.example.whelp.model.AuthResponse
import com.example.whelp.model.UserCredentials
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth")
    suspend fun auth(@Body auth: UserCredentials) : AuthResponse

}