package com.example.microformastest.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.microformastest.presentation.screens.login.LoginScreenRoot
import com.example.microformastest.presentation.screens.login.LoginScreenViewModel
import com.example.microformastest.presentation.screens.services.ServicesScreenRoot
import com.example.microformastest.presentation.screens.services.ServicesScreenViewModel
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val authViewModel: LoginScreenViewModel = hiltViewModel()
        val destinationScreen =
            if (authViewModel.state.isLogged) ServicesScreenDest else LoginScreenDest

        NavHost(
            navController = navController,
            startDestination = destinationScreen
        ) {
            composable<LoginScreenDest> {
                LoginScreenRoot(
                    onNavigateToServicesScreen = {
                        navController.navigate(ServicesScreenDest)
                    },
                    viewModel = authViewModel
                )
            }

            composable<ServicesScreenDest> {
                val viewModel: ServicesScreenViewModel = hiltViewModel()

                ServicesScreenRoot(
                    OnNavigateToLoginScreen = {
                        authViewModel.logOut()
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}

@Serializable
object LoginScreenDest

@Serializable
object ServicesScreenDest
