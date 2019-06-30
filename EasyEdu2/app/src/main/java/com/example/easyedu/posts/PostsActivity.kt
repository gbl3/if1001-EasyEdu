package com.example.easyedu.posts

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.R
import com.example.easyedu.database.RoomDB
import kotlinx.android.synthetic.main.activity_posts.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PostsActivity : AppCompatActivity() {

    var turmas: Array<Post>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        btnAddPost.setOnClickListener {
            val intent = Intent(this, AdicionarPostActivity::class.java)
            startActivity(intent)
        }

//        val qtdTurmas = 20
//
//        for (i in 1..qtdTurmas) {
//            val turma = Button(this)
//
//            turma_screen.addView(turma)
//        }


    }

    override fun onResume() {
        super.onResume()
        val db = RoomDB.getDatabase(this)
        doAsync {
            val posts = db.roomDAO().todosPosts()
            uiThread {
                val adapter = ArrayAdapter<Post> (
                    applicationContext,
                    R.layout.post,
                    posts
                )
                listaPosts.setAdapter(adapter)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        var builder = AlertDialog.Builder(this@PostsActivity)

        builder.setTitle("LOGOUT")
        builder.setMessage("VOCê TEM CERTEZA QUE QUER FAZER LOGOUT?")
        builder.setPositiveButton("Sim"){dialog, which ->
            Toast.makeText(applicationContext,"Ok, we change the app background.", Toast.LENGTH_SHORT).show()



        }
        builder.setNegativeButton("No"){dialog,which ->
            Toast.makeText(applicationContext,"You are not agree.",Toast.LENGTH_SHORT).show()
        }


        // Display a neutral button on alert dialog
        builder.setNeutralButton("Cancel"){_,_ ->
            Toast.makeText(applicationContext,"You cancelled the dialog.",Toast.LENGTH_SHORT).show()
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }
}
