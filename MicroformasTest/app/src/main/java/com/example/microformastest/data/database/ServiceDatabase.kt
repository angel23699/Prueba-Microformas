package com.example.microformastest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ServiceEntity::class],
    version = 1,
)
abstract class ServiceDatabase : RoomDatabase() {
    abstract fun serviceDao() : ServiceDAO
}