package com.example.easyedu.prova


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.easyedu.R
import kotlinx.android.synthetic.main.prova_item.view.*

class AdapterProvas(private val listaDeProvas: Array<Prova>,
                    private val context: Context) : Adapter<AdapterProvas.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prova = listaDeProvas[position]
        holder?.let {
            it.tituloProva.text = prova.titulo
        }

        holder.tituloProva.setOnClickListener{
            val newIntent = Intent(context, ExibeProvaActivity::class.java)
            newIntent.putExtra("idProva", prova.id.toString())
            newIntent.putExtra("tituloProva", prova.titulo)
            context.startActivity(newIntent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.prova_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaDeProvas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloProva = itemView.tituloProva
    }
}