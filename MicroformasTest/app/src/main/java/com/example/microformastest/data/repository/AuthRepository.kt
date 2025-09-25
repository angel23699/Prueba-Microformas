package com.example.microformastest.data.repository

import com.example.microformastest.data.api.ApiService
import com.example.microformastest.data.api.models.login.LoginRequestModel
import com.example.microformastest.data.api.models.login.LoginResponseModel
import com.example.microformastest.domain.interfaces.AuthRepository
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor (private val api: ApiService) : AuthRepository {
    override suspend fun makeLogin(payload: LoginRequestModel): LoginResponseModel {
        return api.callLogin(payload)
    }
}