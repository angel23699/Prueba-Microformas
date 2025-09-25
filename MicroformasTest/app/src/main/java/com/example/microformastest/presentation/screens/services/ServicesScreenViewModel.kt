package com.example.microformastest.presentation.screens.services

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microformastest.domain.interfaces.ServiceRepository
import com.example.microformastest.domain.interfaces.ServicesLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@HiltViewModel
class ServicesScreenViewModel @Inject constructor(
    private val servicesRepository: ServiceRepository,
    private val serviceLocalDataSource: ServicesLocalDataSource
) : ViewModel() {

    var state by mutableStateOf(ServicesScreenDataState())
        private set

    private val eventChannel = Channel<ServicesScreenEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        onAction(ServicesScreenAction.fetchServices)

        serviceLocalDataSource.servicesFlow
            .onEach {
                state = state.copy(services = it)
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: ServicesScreenAction) {
        viewModelScope.launch {
            when (action) {

                ServicesScreenAction.fetchServices -> {
                    state = state.copy(isLoading = true)
                    state = state.copy(error = null)

                    try {
                        val services = servicesRepository.fetchServices()
                        state = state.copy(services = services)
                        saveServicesOnDisk()
                    } catch (e: IOException) {
                        state = state.copy(error = e.localizedMessage)
                    } catch (e: HttpException) {
                        state = state.copy(error = e.localizedMessage)
                    } catch (e: Exception) {
                        state = state.copy(error = e.localizedMessage)
                    } finally {
                        state = state.copy(isLoading = false)
                    }
                }

                else -> Unit
            }
        }
    }

    private fun saveServicesOnDisk() {
        viewModelScope.launch {
            state.services.onEach {
                serviceLocalDataSource.add(it)
            }
        }
    }
}