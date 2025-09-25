package com.example.microformastest.data.api

import com.example.microformastest.data.api.models.login.LoginRequestModel
import com.example.microformastest.data.api.models.login.LoginResponseModel
import com.example.microformastest.data.api.models.services.ServicesResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("sgsmicroformas.login/api/auth/token")
    suspend fun callLogin(@Body payload: LoginRequestModel): LoginResponseModel

    @GET("SgsMicroformas.Mobile.Cat/api/Ars/Technical")
    suspend fun getServices(
        @Query("Id") id: String,
        @Query("Source") source: String
    ): ServicesResponseModel
}