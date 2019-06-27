package com.example.easyedu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterTurmas(private val listTurmas: ArrayList<Turma>) : RecyclerView.Adapter<AdapterTurmas.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTurmas.MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_settings_child, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.textViewName.text = seriesList[position].name
//        holder.textViewNameDesc.text = seriesList[position].desc

    }

    override fun getItemCount(): Int {
        return listTurmas.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.name) as TextView
        val textViewNameDesc = itemView.findViewById(R.id.desc) as TextView

    }
}