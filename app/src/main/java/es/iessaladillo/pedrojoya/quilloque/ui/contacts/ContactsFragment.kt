package es.iessaladillo.pedrojoya.quilloque.ui.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.AppDatabase
import es.iessaladillo.pedrojoya.quilloque.ui.createContact.CreateContactViewModel
import es.iessaladillo.pedrojoya.quilloque.ui.createContact.CreateContactViewModelFactory
import es.iessaladillo.pedrojoya.quilloque.ui.recent.RecentAdapter
import kotlinx.android.synthetic.main.contacts_fragment.*
import kotlinx.android.synthetic.main.recent_fragment.*

class ContactsFragment : Fragment(R.layout.contacts_fragment) {
    private val viewModel: ContactsViewModel by viewModels {
        ContactsViewModelFactory(AppDatabase.getInstance(this.requireContext()).contactoDao)
    }

    val listAdapter: ContactsAdapter = ContactsAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(requireView())
    }

    private fun setupViews(view: View){
        viewModel.actualizarListaContactos()
        submitData()
        setupRecyclerView()
        setupSearchBar()
    }

    private fun submitData(){
        viewModel.listaContactosObserve.observe(viewLifecycleOwner){
            listAdapter.submitData(it)
        }
    }

    private fun setupSearchBar(){
        txtSearch.addTextChangedListener { viewModel.busquedaContacto(txtSearch.text.toString()) }
    }

    private fun setupRecyclerView(){
        lstContacts.setHasFixedSize(true)
        lstContacts.layoutManager = LinearLayoutManager(context)
        lstContacts.itemAnimator = DefaultItemAnimator()
        lstContacts.adapter = listAdapter
    }
}
