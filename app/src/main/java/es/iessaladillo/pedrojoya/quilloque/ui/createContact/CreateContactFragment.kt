package es.iessaladillo.pedrojoya.quilloque.ui.createContact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.AppDatabase
import es.iessaladillo.pedrojoya.quilloque.ui.dial.DialAdapter
import es.iessaladillo.pedrojoya.quilloque.ui.dial.DialViewModel
import es.iessaladillo.pedrojoya.quilloque.ui.dial.DialViewModelFactory
import kotlinx.android.synthetic.main.contact_creation_fragment.*
import kotlinx.android.synthetic.main.contact_creation_fragment.view.*


class CreateContactFragment : Fragment(R.layout.contact_creation_fragment) {

    private val viewModel: CreateContactViewModel by viewModels {
        CreateContactViewModelFactory(AppDatabase.getInstance(this.requireContext()).contactoDao)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(requireView())
    }

    private fun setupViews(view: View){
        setupButtons()
    }

    private fun setupButtons(){
        fabSave.setOnClickListener{ insertarContacto() }
    }

    private fun insertarContacto(){
        if(txtName.text.toString().length > 0 &&
                txtPhoneNumber.text.toString().length > 0){
            viewModel.insertarContacto(txtName.text.toString(), txtPhoneNumber.text.toString())
        }
    }
}
