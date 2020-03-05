package es.iessaladillo.pedrojoya.quilloque.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactoDao {
    @Insert
    fun insertContacto(contacto: Contacto)

    @Query("SELECT * FROM contacto")
    fun queryAllContacts(): List<Contacto>

    @Query("SELECT * FROM contacto WHERE nombre LIKE :name")
    fun queryContactsByName(name: String): List<Contacto>

    @Delete
    fun deleteContacto(contacto: Contacto)
}