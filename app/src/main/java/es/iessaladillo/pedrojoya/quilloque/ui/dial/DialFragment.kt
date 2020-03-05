package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.AppDatabase
import es.iessaladillo.pedrojoya.quilloque.database.LLamada
import es.iessaladillo.pedrojoya.quilloque.pojo.LLamadaContacto
import es.iessaladillo.pedrojoya.quilloque.pojo.Sugerencia
import kotlinx.android.synthetic.main.contacts_fragment.*
import kotlinx.android.synthetic.main.dial_fragment.*
import kotlinx.android.synthetic.main.main_activity.*
import java.text.SimpleDateFormat
import java.util.*

class DialFragment : Fragment(R.layout.dial_fragment), DialAdapter.OnItemClickListener {

    private val navController: NavController by lazy{
        findNavController()
    }

    private val viewModel: DialViewModel by viewModels {
        DialViewModelFactory(AppDatabase.getInstance(this.requireContext()).llamadaDao)
    }

    val listAdapter: DialAdapter = DialAdapter().also {
        it.setOnItemClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(requireView())
    }

    private fun setupViews(view: View){
        setupRecyclerView()
        lblCreateContact.isVisible = false
        submitData()
        setupButtons()
    }

    private fun submitData(){
        viewModel.listaSugerenciasOberserve.observe(viewLifecycleOwner){
            confEmptyView(it)
            listAdapter.submitData(it)
        }
    }

    private fun setupButtons(){
        //TECLADO NUMÉRICO
        lblOne.setOnClickListener{  lblNumber.setText(getCurrentNumber() + "1"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblTwo.setOnClickListener{  lblNumber.setText(getCurrentNumber() + "2"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblThree.setOnClickListener{  lblNumber.setText(getCurrentNumber() + "3"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblFour.setOnClickListener{  lblNumber.setText(getCurrentNumber() + "4"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblFive.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "5"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblSix.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "6"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblSeven.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "7"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblEight.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "8"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblNine.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "9"); viewModel.buscarLLamadas(getCurrentNumber()) }
        lblZero.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "0"); viewModel.buscarLLamadas(getCurrentNumber()) }
        imgBackspace.setOnClickListener{ lblNumber.setText(removeOneNumber()); viewModel.buscarLLamadas(getCurrentNumber()) }

        //OTROS
        lblCreateContact.setOnClickListener{ navigateToCreateContact() }
        fabCall.setOnClickListener{ insertarLLamada() }

    }

    private fun getCurrentNumber(): String{
        return lblNumber.text.toString();
    }

    private fun removeOneNumber(): String{
        if(lblNumber.text.toString().length > 0){
            return lblNumber.text.toString().substring(0, lblNumber.text.toString().length -1)
        }
        return "";
    }

    private fun navigateToCreateContact(){
        navController.navigate(R.id.createContactDestination, bundleOf(
            getString(R.string.number_key) to getCurrentNumber()
        ))
    }

    private fun insertarLLamada(){
        val fecha: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val hora: SimpleDateFormat = SimpleDateFormat("hh:mm")

        if(getCurrentNumber().length > 0){
            viewModel.insertLLamada("saliente", getCurrentNumber(), fecha.format(Date()), hora.format(Date()))
        }
    }

    private fun setupRecyclerView(){
        lstSuggestions.setHasFixedSize(true)
        lstSuggestions.layoutManager = LinearLayoutManager(context)
        lstSuggestions.itemAnimator = DefaultItemAnimator()
        lstSuggestions.adapter = listAdapter
    }

    private fun confEmptyView(lista: List<Sugerencia>){
        if(lista.size > 0 || getCurrentNumber().isNullOrEmpty()){
            lblCreateContact.isVisible = false
        }
        else{
            lblCreateContact.isVisible = true
        }
    }

    override fun onClick(llamada: Sugerencia) {
        lblNumber.setText(llamada.numero)
    }
}
