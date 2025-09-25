package com.example.microformastest.presentation.screens.services

import com.example.microformastest.domain.Service

data class ServicesScreenDataState(
    val services: List<Service> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)