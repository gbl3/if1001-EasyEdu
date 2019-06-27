package com.example.easyedu

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
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
        val db = PostsDB.getDatabase(this)
        doAsync {
            val posts = db.postsDAO().todosPosts()
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
}
