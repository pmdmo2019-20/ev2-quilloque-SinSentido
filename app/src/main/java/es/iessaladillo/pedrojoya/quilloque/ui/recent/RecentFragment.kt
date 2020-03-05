package es.iessaladillo.pedrojoya.quilloque.ui.recent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.AppDatabase
import es.iessaladillo.pedrojoya.quilloque.database.LLamada
import es.iessaladillo.pedrojoya.quilloque.ui.dial.DialAdapter
import es.iessaladillo.pedrojoya.quilloque.ui.dial.DialViewModel
import es.iessaladillo.pedrojoya.quilloque.ui.dial.DialViewModelFactory
import kotlinx.android.synthetic.main.recent_fragment.*

class RecentFragment : Fragment(R.layout.recent_fragment) {

    private val viewModel: RecentViewModel by viewModels {
        RecentViewModelFactory(AppDatabase.getInstance(this.requireContext()).llamadaDao)
    }

    val listAdapter: RecentAdapter = RecentAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(requireView())
    }

    private fun setupViews(view: View){
        viewModel.actualizarListaLLamadas()
        setupRecyclerView()
        submitData()
    }

    private fun submitData(){
        viewModel.listaLLamadasObserve.observe(viewLifecycleOwner){
            confEmptyView(it)
            listAdapter.submitData(it)
        }
    }

    private fun setupRecyclerView(){
        lstCalls.setHasFixedSize(true)
        lstCalls.layoutManager = LinearLayoutManager(context)
        lstCalls.itemAnimator = DefaultItemAnimator()
        lstCalls.adapter = listAdapter
    }

    private fun confEmptyView(lista: List<LLamada>){
        if(lista.size > 0){
            emptyView.isVisible = false
        }
        else{
            emptyView.isVisible = true
        }
    }
}
