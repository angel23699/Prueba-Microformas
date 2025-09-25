package com.example.microformastest.domain.interfaces

import com.example.microformastest.data.api.models.login.LoginRequestModel
import com.example.microformastest.data.api.models.login.LoginResponseModel

interface AuthRepository {
    suspend fun makeLogin(payload: LoginRequestModel): LoginResponseModel
}