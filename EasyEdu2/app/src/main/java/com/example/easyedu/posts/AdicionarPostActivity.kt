package com.example.easyedu.posts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.R
import com.example.easyedu.database.RoomDB
import kotlinx.android.synthetic.main.adicionar_post.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionarPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adicionar_post)
        btnInserirPost.setOnClickListener {
            val intent = Intent(this, PostsActivity::class.java)
            startActivity(intent)
            val postId = txtId.text.toString()
            val postMsg = txtMsg.text.toString()
            val post = Post(postId, postMsg)
            doAsync {
                val db = RoomDB.getDatabase(applicationContext)
                db.roomDAO().inserirPosts(post)
                uiThread {
                    finish()
                }
            }
        }
    }
}
