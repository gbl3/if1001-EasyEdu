package com.example.easyedu.prova

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import com.example.easyedu.prova.questoes.InserirQuestaoActivity
import kotlinx.android.synthetic.main.adicionar_prova.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionarProvaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adicionar_prova)


        btnInserirQuestao.setOnClickListener {
            val intent = Intent(this, InserirQuestaoActivity::class.java)
            startActivity(intent)
        }

        btnCriarProva.setOnClickListener {
            var idTurma: String = intent.getStringExtra("idTurma")
            val tituloProva = txtTituloProva.text.toString()
            val prova = Prova(0, titulo = tituloProva, questaoId = 0)
            doAsync {
                val db = EasyEduDB.getDatabase(applicationContext)
                db.provasDao().inserirProva(prova)
                uiThread {
                    finish()
                }
            }
        }
    }
}
