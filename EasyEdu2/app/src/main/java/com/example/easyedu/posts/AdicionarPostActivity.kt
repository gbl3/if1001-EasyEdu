package com.example.easyedu.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import kotlinx.android.synthetic.main.adicionar_post.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionarPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adicionar_post)
        btnInserirPost.setOnClickListener {
            var idTurma: String = intent.getStringExtra("idTurma")
            val postId = txtId.text.toString()
            val postMsg = txtMsg.text.toString()
            val post = Post(postId, postMsg, idTurma.toInt())
            doAsync {
                val db = EasyEduDB.getDatabase(applicationContext)
                db.postsDAO().inserirPosts(post)
                uiThread {
                    finish()
                }
            }
        }
    }
}
