package es.iessaladillo.pedrojoya.quilloque.ui.createContact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.quilloque.database.ContactoDao
import es.iessaladillo.pedrojoya.quilloque.ui.dial.DialViewModel

class CreateContactViewModelFactory(val contactoDao: ContactoDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CreateContactViewModel(contactoDao) as T
}