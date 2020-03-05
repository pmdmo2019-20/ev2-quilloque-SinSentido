package es.iessaladillo.pedrojoya.quilloque.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LLamadaDao {
    @Insert
    fun insertLLamada(llamada: LLamada)

    @Query("SELECT * FROM llamada")
    fun queryAllLLamadas(): List<LLamada>

    @Query("SELECT * FROM llamada WHERE numero LIKE :num")
    fun queryAllLLamadasByNumber(num: String): List<LLamada>
}