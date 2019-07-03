package com.example.easyedu.posts


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.easyedu.R
import kotlinx.android.synthetic.main.post.view.*

class AdapterPosts(private val listaDePosts: Array<Post>,
                   private val context: Context) : Adapter<AdapterPosts.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = listaDePosts[position]
        holder?.let {
            it.msgPost.text = post.msg
        }

        holder.msgPost.setOnClickListener{
            val newIntent = Intent(context, ExibePostActivity::class.java)
            newIntent.putExtra("msgPost", post.msg)
            context.startActivity(newIntent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaDePosts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val msgPost = itemView.nomePost
    }
}