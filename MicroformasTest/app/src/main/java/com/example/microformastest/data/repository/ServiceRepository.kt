package com.example.microformastest.data.repository

import com.example.microformastest.data.api.ApiService
import com.example.microformastest.domain.DataStorage_plus_Tink.DataStoreManager
import com.example.microformastest.domain.Service
import com.example.microformastest.domain.interfaces.ServiceRepository
import com.example.microformastest.utils.ConstantsUtils
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first

class ServiceRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val secureDataStore: DataStoreManager
) : ServiceRepository {
    override suspend fun fetchServices() : List<Service> {
        val userId = secureDataStore.getString(ConstantsUtils.USER_ID_KEY).first() ?: ""
        val source = secureDataStore.getString(ConstantsUtils.SOURCE_NAME_KEY).first() ?: ""
        return api.getServices(userId, source).data ?: emptyList()
    }
}