package es.iessaladillo.pedrojoya.quilloque.ui.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.database.LLamada
import es.iessaladillo.pedrojoya.quilloque.database.LLamadaDao
import kotlin.concurrent.thread

class RecentViewModel(val llamadaDao: LLamadaDao): ViewModel() {

    var listaLLamadas: MutableLiveData<List<LLamada>> = MutableLiveData()
    val listaLLamadasObserve: LiveData<List<LLamada>> get() = listaLLamadas

    fun actualizarListaLLamadas(){
        thread {
            listaLLamadas.postValue(llamadaDao.queryAllLLamadas())
        }
    }
}