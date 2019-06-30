package com.example.easyedu.turmas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.R
import com.example.easyedu.database.RoomDB
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
                val db = RoomDB.getDatabase(applicationContext)
                db.roomDAO().inserirTurmas(turma)
                uiThread {
                    finish()
                }
            }
        }
    }
}
