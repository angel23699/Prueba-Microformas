package com.example.microformastest.presentation.screens.login

sealed class LoginScreenEvent {
    data object LoginDone : LoginScreenEvent()
    data class ShowErrorMsg(val msg: String) : LoginScreenEvent()
}