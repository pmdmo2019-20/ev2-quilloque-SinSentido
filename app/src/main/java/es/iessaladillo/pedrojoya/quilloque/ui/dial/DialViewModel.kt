package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Insert
import es.iessaladillo.pedrojoya.quilloque.database.LLamada
import es.iessaladillo.pedrojoya.quilloque.database.LLamadaDao
import java.util.*
import kotlin.concurrent.thread

class DialViewModel(val llamadaDao: LLamadaDao): ViewModel() {

    var listaSugerencias: MutableLiveData<List<LLamada>> = MutableLiveData()
    val listaSugerenciasOberserve: LiveData<List<LLamada>> get() = listaSugerencias

    fun cargarSugerencias( ) {
        thread {

        }
    }

    fun insertLLamada(tipo: String, numero: String, fecha: String, hora: String){
        thread {
            llamadaDao.insertLLamada(LLamada(0, tipo, numero, fecha, hora))
        }
    }

}