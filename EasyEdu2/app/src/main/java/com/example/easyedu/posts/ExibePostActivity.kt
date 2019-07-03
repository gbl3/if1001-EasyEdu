package com.example.easyedu.posts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import com.example.easyedu.prova.questoes.AdapterQuestoes
import com.example.easyedu.prova.questoes.InserirQuestaoActivity
import kotlinx.android.synthetic.main.activity_exibe_post.*
import kotlinx.android.synthetic.main.activity_exibe_prova.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ExibePostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibe_prova)

        var msgPost: String = intent.getStringExtra("msgPost")
        tituloPostAtual.text = msgPost


    }

}
