package es.iessaladillo.pedrojoya.quilloque.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.DrawableUtils
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.Contacto
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer
import org.w3c.dom.Text

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    var listaContactos: List<Contacto> = listOf()
    private var onItemClickListener: ContactsAdapter.OnItemClickListener? = null

    fun submitData(nuevaLista: List<Contacto>){
        listaContactos = nuevaLista
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: ContactsAdapter.OnItemClickListener){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.contacts_fragment_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = listaContactos.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var contacto: Contacto = listaContactos[position]
        holder.bind(contacto)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        val imgAvatar: ImageView = containerView.findViewById(R.id.imgAvatar)
        val lblName: TextView = containerView.findViewById(R.id.lblName)
        val lblPhoneNumber: TextView = containerView.findViewById(R.id.lblPhoneNumber)
        val btnDelete: ImageButton = containerView.findViewById(R.id.btnDelete)

        init {
            btnDelete.setOnClickListener{ onItemClickListener?.onClick(listaContactos[adapterPosition]) }
        }

        fun bind(contacto: Contacto){
            imgAvatar.setImageDrawable(createAvatarDrawable(contacto.nombre))
            lblName.setText(contacto.nombre)
            lblPhoneNumber.setText(contacto.numero)
        }
    }

    interface OnItemClickListener{
        fun onClick(contacto: Contacto)
    }
}