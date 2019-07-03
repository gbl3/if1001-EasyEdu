package com.example.easyedu.prova.questoes


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.easyedu.R
import kotlinx.android.synthetic.main.questao_item.view.*

class AdapterQuestoes(private val listaDeQuestoes: Array<Questao>,
                      private val context: Context) : Adapter<AdapterQuestoes.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val questao = listaDeQuestoes[position]
        holder?.let {
            it.enunciadoQuestao.text = questao.enunciado
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.questao_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaDeQuestoes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val enunciadoQuestao = itemView.enunciadoQuestao
    }
}