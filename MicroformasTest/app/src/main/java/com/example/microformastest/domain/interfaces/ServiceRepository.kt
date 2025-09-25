package com.example.microformastest.domain.interfaces

import com.example.microformastest.domain.Service


interface ServiceRepository {
    suspend fun fetchServices() : List<Service>
}
