package com.example.whelp

data class UserCredentials(val language: String, val contract: Contract)

data class Contract(
    val email: String,
    val fullname: String,
    val phone: String,
    val account_type: String,
    val pricing_plan: String
)