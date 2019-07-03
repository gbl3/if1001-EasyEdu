package com.example.easyedu.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.R
import kotlinx.android.synthetic.main.activity_exibe_post.*

class ExibePostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibe_post)

        var msgPost: String = intent.getStringExtra("msgPost")
        tituloPostAtual.text = msgPost
    }

}
