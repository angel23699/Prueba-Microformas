package com.example.microformastest.presentation.screens.services

sealed class ServicesScreenAction {
    data object fetchServices : ServicesScreenAction()

    data object LogOut : ServicesScreenAction()
}