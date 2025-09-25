package com.example.microformastest.presentation.screens.login

data class LoginDataState(
    val username : String = "",
    val password : String = "",
    val isLoading: Boolean = false,
    val isLogged: Boolean = false,
)
