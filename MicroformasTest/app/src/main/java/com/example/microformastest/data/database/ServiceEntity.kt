package com.example.microformastest.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.microformastest.domain.Service
import java.time.ZoneId

@Entity(tableName = "services")
data class ServiceEntity(
    @PrimaryKey(autoGenerate = false)
    val idAr: Int,

    @ColumnInfo(name = "desc_cliente")
    val descCliente: String,

    @ColumnInfo(name = "desc_servicio")
    val descServicio: String,

    val direccion: String,
    val colonia: String,
    val poblacion: String,
    val estado: String,
    val cp: String,
    val telefono: String,
) {
    fun toService(): Service {
        return Service(
            idAr,
            descCliente,
            descServicio,
            direccion,
            colonia,
            poblacion,
            estado,
            cp,
            telefono
        )
    }

    companion object {
        fun fromService(task: Service): ServiceEntity {
            return ServiceEntity(
                idAr = task.idAr,
                descCliente = task.descCliente,
                descServicio = task.descServicio,
                direccion = task.direccion,
                colonia = task.colonia,
                poblacion = task.poblacion,
                estado = task.estado,
                cp = task.cp,
                telefono = task.telefono
            )
        }
    }
}