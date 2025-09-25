package com.example.microformastest.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDAO {
    @Query("SELECT * FROM services")
    fun getAllServices() : Flow<List<ServiceEntity>>

    @Upsert
    suspend fun upsertService(service: ServiceEntity)

    @Query("DELETE FROM services")
    suspend fun deleteAllServices()
}