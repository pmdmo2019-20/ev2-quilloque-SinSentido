package es.iessaladillo.pedrojoya.quilloque.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.database.Contacto
import es.iessaladillo.pedrojoya.quilloque.database.ContactoDao
import kotlin.concurrent.thread

class ContactsViewModel(val contactoDao: ContactoDao): ViewModel() {

    var listaContactos: MutableLiveData<List<Contacto>> = MutableLiveData()
    val listaContactosObserve: LiveData<List<Contacto>> get() = listaContactos

    fun actualizarListaContactos(){
        thread {
            listaContactos.postValue(contactoDao.queryAllContacts())
        }
    }

    fun busquedaContacto(name: String){
        thread {
            listaContactos.postValue(contactoDao.queryContactsByName(name))
        }
    }
}