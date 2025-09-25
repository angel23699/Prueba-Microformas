package com.example.microformastest.presentation.screens.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microformastest.data.api.models.login.LoginRequestModel
import com.example.microformastest.data.api.models.login.LoginResponseModel
import com.example.microformastest.domain.DataStorage_plus_Tink.DataStoreManager
import com.example.microformastest.domain.interfaces.AuthRepository
import com.example.microformastest.utils.ConstantsUtils
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val secureDataStore: DataStoreManager,
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(LoginDataState())
        private set

    private val eventChannel = Channel<LoginScreenEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            secureDataStore.getString(ConstantsUtils.ACCESS_TOKEN_KEY).collect { token ->
                state = state.copy(isLogged = token != null)
            }
        }
    }

    fun onAction(action: LoginScreenAction) {
        viewModelScope.launch {
            when (action) {
                is LoginScreenAction.OnUsernameChanged -> {
                    state = state.copy(username = action.value)
                }

                is LoginScreenAction.OnPasswordChanged -> {
                    state = state.copy(password = action.value)
                }

                is LoginScreenAction.OnLogin -> {
                    makeLoginRequest()
                }
            }
        }
    }

    private suspend fun makeLoginRequest() {
        state = state.copy(isLoading = true)

        try {
            val payload = LoginRequestModel(username = state.username, password = state.password)
            val response = authRepository.makeLogin(payload)
            if ((response.access_token ?: "").trim().isNotEmpty()) {

                secureDataStore.putString(
                    ConstantsUtils.ACCESS_TOKEN_KEY,
                    response.access_token ?: ""
                )
                secureDataStore.putString(ConstantsUtils.USER_ID_KEY, response.userId ?: "")
                secureDataStore.putString(ConstantsUtils.SOURCE_NAME_KEY, response.descEnv ?: "")

                eventChannel.send(LoginScreenEvent.LoginDone)
            } else if ((response.messages ?: emptyList()).isEmpty()) {
                eventChannel.send(LoginScreenEvent.ShowErrorMsg(response.messages.toString()))
            }
        } catch (error: Exception) {
            if (error is HttpException) {
                val errorJsonString = error.response()?.errorBody()?.string() ?: ""
                val errorResponse = Gson().fromJson(errorJsonString, LoginResponseModel::class.java)
                val errorMessage = errorResponse.messages?.joinToString() ?: "Unknown Error"

                Log.e("LoginScreenViewModel", "Error: $errorMessage")
                eventChannel.send(LoginScreenEvent.ShowErrorMsg(errorMessage))
            } else {
                Log.e("LoginScreenViewModel", "Error: ${error.localizedMessage}")
                eventChannel.send(
                    LoginScreenEvent.ShowErrorMsg(
                        error.localizedMessage ?: "Unknown Error"
                    )
                )
            }
        } finally {
            state = state.copy(isLoading = false)
        }
    }

    fun logOut() {
        viewModelScope.launch {
            secureDataStore.clearString(ConstantsUtils.ACCESS_TOKEN_KEY)
            secureDataStore.clearString(ConstantsUtils.USER_ID_KEY)
            secureDataStore.clearString(ConstantsUtils.SOURCE_NAME_KEY)
            state = state.copy(isLogged = false)
        }
    }
}