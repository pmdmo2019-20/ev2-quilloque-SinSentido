package es.iessaladillo.pedrojoya.quilloque.ui.recent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.LLamada
import kotlinx.android.extensions.LayoutContainer

class RecentAdapter: RecyclerView.Adapter<RecentAdapter.ViewHolder>() {

    var listaLLamadas: List<LLamada> = listOf()

    fun submitData(nuevaLista: List<LLamada>){
        listaLLamadas = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recent_fragment_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = listaLLamadas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var llamada: LLamada = listaLLamadas[position]
        holder.bind(llamada)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val lblName: TextView = containerView.findViewById(R.id.lblName)
        val lblPhoneNumber: TextView = containerView.findViewById(R.id.lblPhoneNumber)
        val lblTime: TextView = containerView.findViewById(R.id.lblTime)
        val lblDate: TextView = containerView.findViewById(R.id.lblDate)

        fun bind(llamada: LLamada){
            lblName.setText(llamada.numero)
            lblTime.setText(llamada.hora)
            lblDate.setText(llamada.fecha)
        }

    }
}