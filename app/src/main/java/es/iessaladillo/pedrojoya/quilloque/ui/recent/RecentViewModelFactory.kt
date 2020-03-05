package es.iessaladillo.pedrojoya.quilloque.ui.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.quilloque.database.LLamadaDao
import es.iessaladillo.pedrojoya.quilloque.ui.dial.DialViewModel

class RecentViewModelFactory(val llamadaDao: LLamadaDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        RecentViewModel(llamadaDao) as T
}