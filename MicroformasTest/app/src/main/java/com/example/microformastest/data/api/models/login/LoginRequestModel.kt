package com.example.microformastest.data.api.models.login

data class LoginRequestModel(
    val username: String,
    val password: String,
    val origin: String = "mobile",
    val version: String = "1.3.1",
    val fcmToken: String = "Test",
)
