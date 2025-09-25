package com.example.microformastest.presentation.screens.services

sealed class ServicesScreenEvent {
    data object fetchServicesRemote : ServicesScreenEvent()
}