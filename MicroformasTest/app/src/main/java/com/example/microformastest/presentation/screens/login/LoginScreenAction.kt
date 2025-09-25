package com.example.microformastest.presentation.screens.login

sealed class LoginScreenAction {

    data class OnUsernameChanged(val value: String) : LoginScreenAction()

    data class OnPasswordChanged(val value: String) : LoginScreenAction()

    data object OnLogin : LoginScreenAction()
}