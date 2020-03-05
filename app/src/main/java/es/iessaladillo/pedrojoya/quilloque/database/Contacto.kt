package es.iessaladillo.pedrojoya.quilloque.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacto")
data class Contacto (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idContacto")
    val idContacto: Int,
    @ColumnInfo(name = "nombre")
    var nombre: String,
    @ColumnInfo(name = "numero")
    var numero: String
)