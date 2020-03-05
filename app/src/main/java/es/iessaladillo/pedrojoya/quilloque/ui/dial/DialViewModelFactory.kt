package es.iessaladillo.pedrojoya.quilloque.ui.dial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.quilloque.database.LLamadaDao

class DialViewModelFactory(val llamadaDao: LLamadaDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DialViewModel(llamadaDao) as T
}