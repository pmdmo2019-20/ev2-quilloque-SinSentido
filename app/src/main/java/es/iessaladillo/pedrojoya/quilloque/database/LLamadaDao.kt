package es.iessaladillo.pedrojoya.quilloque.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iessaladillo.pedrojoya.quilloque.pojo.LLamadaContacto

@Dao
interface LLamadaDao {
    @Insert
    fun insertLLamada(llamada: LLamada)

    @Query("SELECT * FROM llamada")
    fun queryAllLLamadas(): List<LLamada>

    @Query("SELECT * FROM llamada WHERE numero LIKE :num")
    fun queryAllLLamadasByNumber(num: String): List<LLamada>

    @Query("SELECT ll.idLLamada AS idLLamada, ll.tipo AS tipo, ll.numero AS numeroLLamada, ll.fecha AS fecha ,ll.hora AS hora, c.idContacto AS idContacto, C.nombre AS nombre, c.numero AS numeroContacto FROM llamada AS ll LEFT JOIN contacto AS c ON c.numero = ll.numero ORDER BY c.idContacto DESC")
    fun queryAllLLamadasWithContacto(): List<LLamadaContacto>

    @Query("SELECT ll.idLLamada AS idLLamada, ll.tipo AS tipo, ll.numero AS numeroLLamada, ll.fecha AS fecha ,ll.hora AS hora, c.idContacto AS idContacto, C.nombre AS nombre, c.numero AS numeroContacto FROM llamada AS ll LEFT JOIN contacto AS c ON c.numero = ll.numero WHERE ll.numero LIKE :num ORDER BY c.idContacto DESC")
    fun queryAllLLamadasWithContactoByNumber(num: String): List<LLamadaContacto>
}