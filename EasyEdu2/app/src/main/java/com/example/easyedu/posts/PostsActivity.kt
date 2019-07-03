package com.example.easyedu.posts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import kotlinx.android.synthetic.main.activity_posts.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PostsActivity : AppCompatActivity() {

    var turmas: Array<Post>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        btnAddPost.setOnClickListener {
            val newIntent = Intent(this, AdicionarPostActivity::class.java)
            val receivedId = intent.getStringExtra("idTurma")
            newIntent.putExtra("idTurma", receivedId)
            startActivity(newIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val idTurma = intent.getStringExtra("idTurma")
            val posts = db.postsDAO().buscaPostPeloIdDaTurma(idTurma.toInt())
            uiThread {
                val recyclerView = listaPosts
                recyclerView.adapter = AdapterPosts(posts, this@PostsActivity.applicationContext)

                val layoutManager = LinearLayoutManager(this@PostsActivity.applicationContext)
                recyclerView.layoutManager = layoutManager
            }
        }

    }

//    override fun onDestroy() {
//        super.onDestroy()
//        var builder = AlertDialog.Builder(this@PostsActivity)
//
//        builder.setTitle("LOGOUT")
//        builder.setMessage("VOCê TEM CERTEZA QUE QUER FAZER LOGOUT?")
//        builder.setPositiveButton("Sim"){dialog, which ->
//            Toast.makeText(applicationContext,"Ok, we change the app background.", Toast.LENGTH_SHORT).show()
//
//
//
//        }
//        builder.setNegativeButton("No"){dialog,which ->
//            Toast.makeText(applicationContext,"You are not agree.",Toast.LENGTH_SHORT).show()
//        }
//
//
//        // Display a neutral button on alert dialog
//        builder.setNeutralButton("Cancel"){_,_ ->
//            Toast.makeText(applicationContext,"You cancelled the dialog.",Toast.LENGTH_SHORT).show()
//        }
//
//        // Finally, make the alert dialog using builder
//        val dialog: AlertDialog = builder.create()
//
//        // Display the alert dialog on app interface
//        dialog.show()
//    }
}
