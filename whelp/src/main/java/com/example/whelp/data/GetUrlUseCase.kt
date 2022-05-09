package com.example.whelp.data

import com.example.whelp.model.UserCredentials
import javax.inject.Inject

class GetUrlUseCase @Inject constructor(val repository: AuthRepository) {
    suspend fun execute(userCredentials: UserCredentials) = repository.auth(userCredentials)
}