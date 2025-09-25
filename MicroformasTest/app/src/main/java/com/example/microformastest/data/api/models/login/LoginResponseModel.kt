package com.example.microformastest.data.api.models.login

data class LoginResponseModel(
    val access_token: String?,
    val userId: String?,
    val descEnv: String?,
    val statusCode: Int?,
    val data: Int?,
    val messages: List<String>?
)