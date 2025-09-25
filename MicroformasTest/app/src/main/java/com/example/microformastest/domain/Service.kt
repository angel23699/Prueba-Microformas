package com.example.microformastest.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Service(
    @SerialName("idAr") val idAr: Int,
    @SerialName("descCliente") val descCliente: String,
    @SerialName("descServicio") val descServicio: String,
    @SerialName("direccion") val direccion: String,
    @SerialName("colonia") val colonia: String,
    @SerialName("poblacion") val poblacion: String,
    @SerialName("estado") val estado: String,
    @SerialName("cp") val cp: String,
    @SerialName("telefono") val telefono: String,
)