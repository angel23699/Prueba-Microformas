package com.example.microformastest.domain.interfaces

import com.example.microformastest.domain.Service
import kotlinx.coroutines.flow.Flow

interface ServicesLocalDataSource {
    val servicesFlow: Flow<List<Service>>
    suspend fun add(service: Service)
    suspend fun deleteAll()
}