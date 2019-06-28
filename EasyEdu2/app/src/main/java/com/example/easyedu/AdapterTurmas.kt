package com.example.easyedu


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.turma_item.view.*

class AdapterTurmas(private val listaDeTurmas: Array<Turma>,
                    private val context: Context) : Adapter<AdapterTurmas.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val turma = listaDeTurmas[position]
        holder?.let {
            it.idTurma.text = turma.id
            it.nomeTurma.text = turma.nome
        }

        holder.nomeTurma.setOnClickListener{
            val intent = Intent(context, ExibeTurmaActivity::class.java)
            intent.putExtra("idTurma", turma.id)
            intent.putExtra("nomeTurma", turma.nome)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.turma_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaDeTurmas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTurma = itemView.turma_item_id
        val nomeTurma = itemView.turma_item_nome
    }
}