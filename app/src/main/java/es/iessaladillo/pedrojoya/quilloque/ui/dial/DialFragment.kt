package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.AppDatabase
import kotlinx.android.synthetic.main.dial_fragment.*
import kotlinx.android.synthetic.main.main_activity.*
import java.text.SimpleDateFormat
import java.util.*

class DialFragment : Fragment(R.layout.dial_fragment) {

    private val navController: NavController by lazy{
        findNavController()
    }

    private val viewModel: DialViewModel by viewModels {
        DialViewModelFactory(AppDatabase.getInstance(this.requireContext()).llamadaDao)
    }

    val listAdapter: DialAdapter = DialAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(requireView())
    }

    private fun setupViews(view: View){
        setupButtons()
    }

    private fun submitData(){
        viewModel.listaSugerenciasOberserve.observe(viewLifecycleOwner){
            listAdapter.submitData(it)
        }
    }

    private fun setupButtons(){
        //TECLADO NUMÉRICO
        lblOne.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "1") }
        lblTwo.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "2") }
        lblThree.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "3") }
        lblFour.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "4") }
        lblFive.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "5") }
        lblSix.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "6") }
        lblSeven.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "7") }
        lblEight.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "8") }
        lblNine.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "9") }
        lblZero.setOnClickListener{ lblNumber.setText(getCurrentNumber() + "0") }
        imgBackspace.setOnClickListener{ lblNumber.setText(removeOneNumber()) }

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
        navController.navigate(R.id.createContactDestination)
    }

    private fun insertarLLamada(){
        val fecha: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val hora: SimpleDateFormat = SimpleDateFormat("hh:mm")

        if(getCurrentNumber().length > 0){
            viewModel.insertLLamada("saliente", getCurrentNumber(), fecha.format(Date()), hora.format(Date()))
        }
    }
}
