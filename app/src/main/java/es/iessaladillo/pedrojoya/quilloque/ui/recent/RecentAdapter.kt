package es.iessaladillo.pedrojoya.quilloque.ui.recent

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.database.LLamada
import es.iessaladillo.pedrojoya.quilloque.pojo.LLamadaContacto
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer

class RecentAdapter: RecyclerView.Adapter<RecentAdapter.ViewHolder>() {

    var listaLLamadas: List<LLamadaContacto> = listOf()

    fun submitData(nuevaLista: List<LLamadaContacto>){
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
        var llamada: LLamadaContacto = listaLLamadas[position]
        holder.bind(llamada)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        val imgAvatar: ImageView = containerView.findViewById(R.id.imgAvatar)
        val imgCallType: ImageView = containerView.findViewById(R.id.imgCallType)
        val lblName: TextView = containerView.findViewById(R.id.lblName)
        val lblPhoneNumber: TextView = containerView.findViewById(R.id.lblPhoneNumber)
        val lblTime: TextView = containerView.findViewById(R.id.lblTime)
        val lblDate: TextView = containerView.findViewById(R.id.lblDate)
        val lblCreateContact: TextView = containerView.findViewById(R.id.lblCreateContact)

        val btnCall: ImageButton = containerView.findViewById(R.id.btnCall)
        val btnMessage: ImageButton = containerView.findViewById(R.id.btnMessage)
        val btnVideoCall: ImageButton = containerView.findViewById(R.id.btnVideoCall)
        val btnDelete: ImageButton = containerView.findViewById(R.id.btnDelete)

        fun bind(llamada: LLamadaContacto){
            setCallTypeImage(llamada)
            if(isContacto(llamada.idContacto)){
                printContacto(llamada)
            }
            else{
                printLLamada(llamada)
            }
        }

        private fun isContacto(idContacto: Int?): Boolean{
            return idContacto != null
        }

        private fun printContacto(llamada: LLamadaContacto){
            imgAvatar.setImageDrawable(createAvatarDrawable(llamada.nombre!!))
            lblName.setText(llamada.nombre)
            lblPhoneNumber.setText(llamada.numeroContacto)
            lblTime.setText(llamada.hora)
            lblDate.setText(llamada.fecha)
            lblCreateContact.isVisible = false;
        }

        private fun printLLamada(llamada: LLamadaContacto){
            imgAvatar.setImageDrawable(createAvatarDrawable(llamada.numeroLLamada))
            lblName.setText(llamada.numeroLLamada)
            lblTime.setText(llamada.hora)
            lblDate.setText(llamada.fecha)
            removeContactMenu()
        }

        private fun removeContactMenu(){
            btnCall.isVisible = false
            btnMessage.isVisible = false
            btnVideoCall.isVisible = false
            btnDelete.isVisible = false
        }

        private fun setCallTypeImage(llamada: LLamadaContacto){
            when(llamada.tipo){
                "saliente" -> imgCallType.setImageDrawable(containerView.resources.getDrawable(R.drawable.ic_call_made_black_24dp))
                "entrante" -> imgCallType.setImageDrawable(containerView.resources.getDrawable(R.drawable.ic_call_received_black_24dp))
                "perdida" -> imgCallType.setImageDrawable(containerView.resources.getDrawable(R.drawable.ic_call_missed_black_24dp))
                "videollamada" -> imgCallType.setImageDrawable(containerView.resources.getDrawable(R.drawable.ic_video_call_black_24dp))
            }
        }

    }
}