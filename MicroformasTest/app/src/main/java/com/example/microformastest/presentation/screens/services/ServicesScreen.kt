@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.microformastest.presentation.screens.services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.microformastest.domain.Service
import com.example.microformastest.presentation.screens.login.LoginScreenAction

@Composable
fun ServicesScreenRoot(
    OnNavigateToLoginScreen: () -> Unit,
    viewModel: ServicesScreenViewModel
) {
    val state = viewModel.state
    val event = viewModel.events

    val context = LocalContext.current

    LaunchedEffect(true) {
        event.collect { event ->
            when (event) {
                ServicesScreenEvent.fetchServicesRemote -> {

                }
            }
        }
    }

    ServicesScreen(
        state = state,
        onAction = {
            when (it) {
                ServicesScreenAction.LogOut -> OnNavigateToLoginScreen()
                ServicesScreenAction.fetchServices -> viewModel.onAction(it)
            }
        }
    )
}

@Composable
fun ServicesScreen(
    mofifier: Modifier = Modifier,
    state: ServicesScreenDataState,
    onAction: (ServicesScreenAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Servicios") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Green
                ),
                actions = {
                    IconButton(onClick = {
                        onAction(ServicesScreenAction.LogOut)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "LogOut"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 4.dp
                        )
                    }
                }
            }

            if (state.error != null && state.services.count() <= 0) {
                item {
                    Text(text = state.error)
                }

                item {
                    Button(
                        onClick = {
                            onAction(ServicesScreenAction.fetchServices)
                        },
                    ) {
                        Text("Reintentar ->")
                    }
                }
            }

            items(state.services) { service ->
                ServiceItemCard(service = service)
            }
        }
    }
}

@Composable
fun ServiceItemCard(
    service: Service,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Folio: ${service.idAr}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = service.descCliente,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Servicio: ${service.descServicio}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Dirección: ${service.direccion}, ${service.colonia}, ${service.poblacion}, ${service.estado}, ${service.cp}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Tel: ${service.telefono}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Light
            )
        }
    }
}
