package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.LLamada
import kotlinx.android.extensions.LayoutContainer

class DialAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listaSugerencias: List<LLamada> = listOf()

    fun submitData(nuevaLista: List<LLamada>){
        listaSugerencias = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.dial_fragment_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = listaSugerencias.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var llamada: LLamada = listaSugerencias[position]
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

    }
}