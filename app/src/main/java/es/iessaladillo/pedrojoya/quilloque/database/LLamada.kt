package es.iessaladillo.pedrojoya.quilloque.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "llamada")
data class LLamada(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idLLamada")
    val idLLamada: Int,
    @ColumnInfo(name = "tipo")
    val tipo: String,
    @ColumnInfo(name = "numero")
    val numero: String,
    @ColumnInfo(name = "fecha")
    val fecha: String,
    @ColumnInfo(name = "hora")
    val hora: String
)