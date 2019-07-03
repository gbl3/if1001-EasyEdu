package com.example.easyedu.turmas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import kotlinx.android.synthetic.main.adicionar_turma.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionarTurmaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adicionar_turma)
        btnInserirTurma.setOnClickListener {
            val turmaNome = txtNome.text.toString()
            val turma = Turma(0, nome = turmaNome)

            doAsync {
                val db = EasyEduDB.getDatabase(applicationContext)
                db.turmasDAO().inserirTurmas(turma)
                uiThread {
                    finish()
                }
            }
        }
    }
}
