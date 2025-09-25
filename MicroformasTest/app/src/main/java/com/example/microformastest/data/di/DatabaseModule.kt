package com.example.microformastest.data.di

import android.content.Context
import androidx.room.Room
import com.example.microformastest.data.database.RoomServiceLocalDataStore
import com.example.microformastest.data.database.ServiceDAO
import com.example.microformastest.data.database.ServiceDatabase
import com.example.microformastest.domain.interfaces.ServicesLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext
        context: Context
    ) : ServiceDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ServiceDatabase::class.java,
            "microformas_app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideServiceDao(
        dataBase: ServiceDatabase
    ) : ServiceDAO = dataBase.serviceDao()

    @Provides
    @Singleton
    fun provideServicesLocalDataSource(
        servicesDao: ServiceDAO,
        dispatcher: CoroutineDispatcher
    ) : ServicesLocalDataSource = RoomServiceLocalDataStore(servicesDao, dispatcher)

}