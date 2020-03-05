package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.LLamada
import es.iessaladillo.pedrojoya.quilloque.ui.contacts.ContactsAdapter
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer
import org.w3c.dom.Text

class DialAdapter: RecyclerView.Adapter<DialAdapter.ViewHolder>() {

    var listaSugerencias: List<LLamada> = listOf()

    fun submitData(nuevaLista: List<LLamada>){
        listaSugerencias = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.dial_fragment_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = listaSugerencias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var llamada: LLamada = listaSugerencias[position]
        holder.bind(llamada)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        val imgAvatar: ImageView = containerView.findViewById(R.id.imgAvatar)
        val lblContactName: TextView = containerView.findViewById(R.id.lblContactName)
        val lblPhoneNumber: TextView = containerView.findViewById(R.id.lblPhoneNumber)

        fun bind(llamada: LLamada){
            imgAvatar.setImageDrawable(createAvatarDrawable(llamada.numero))
            lblContactName.setText(llamada.numero)
            lblPhoneNumber.setText(llamada.numero)
        }
    }
}