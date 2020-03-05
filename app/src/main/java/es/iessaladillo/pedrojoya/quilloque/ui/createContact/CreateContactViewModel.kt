package es.iessaladillo.pedrojoya.quilloque.ui.createContact

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.database.Contacto
import es.iessaladillo.pedrojoya.quilloque.database.ContactoDao
import kotlin.concurrent.thread

class CreateContactViewModel(val contactoDao: ContactoDao): ViewModel() {
    fun insertarContacto(nombre: String, numero: String){
        thread {
            contactoDao.insertContacto(Contacto(0, nombre, numero))
            println("CREANDO CONTACTOOOOOOOOOOOOOOOOOOOOOOO")
        }
    }
}