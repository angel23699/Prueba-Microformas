package com.example.microformastest.data.database

import com.example.microformastest.domain.Service
import com.example.microformastest.domain.interfaces.ServicesLocalDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class RoomServiceLocalDataStore @Inject constructor(
    private val serviceDao: ServiceDAO,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : ServicesLocalDataSource {

    override val servicesFlow: Flow<List<Service>>
        get() = serviceDao.getAllServices()
            .map { it.map { serviceEntity -> serviceEntity.toService() } }
            .flowOn(dispatcherIO)

    override suspend fun add(service: Service) {
        serviceDao.upsertService(ServiceEntity.fromService(service))
    }

    override suspend fun deleteAll() {
        serviceDao.deleteAllServices()
    }
}