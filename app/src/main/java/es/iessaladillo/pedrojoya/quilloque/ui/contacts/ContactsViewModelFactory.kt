package es.iessaladillo.pedrojoya.quilloque.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.quilloque.database.ContactoDao
import es.iessaladillo.pedrojoya.quilloque.ui.createContact.CreateContactViewModel

class ContactsViewModelFactory(val contactoDao: ContactoDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ContactsViewModel(contactoDao) as T
}