package com.example.microformastest.data.api.models.services

import com.example.microformastest.domain.Service

data class ServicesResponseModel(
    val statusCode: Int?,
    val data: List<Service>?,
    val messages: List<String>?
)