package com.example.easyedu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.adicionar_turma.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionarTurmaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adicionar_turma)
        btnInserirTurma.setOnClickListener {
            val turmaId = txtId.text.toString()
            val turmaNome = txtNome.text.toString()
            val turma = Turma(turmaId, turmaNome)
            doAsync {
                val db = EasyEduDB.getDatabase(applicationContext)
                db.easyEduDAO().inserirTurmas(turma)
                uiThread {
                    finish()
                }
            }
        }
    }
}
