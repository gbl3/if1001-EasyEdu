package com.example.easyedu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_turmas.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TurmasActivity : AppCompatActivity() {

    var turmas: Array<Turma>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turmas)

        btnAddTurma.setOnClickListener {
            val intent = Intent(this, AdicionarTurmaActivity::class.java)
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
        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val turmas = db.easyEduDAO().todasTurmas()
            uiThread {
                val adapter = AdapterTurmas(turmas)
//                val adapter = ArrayAdapter<Turma> (
//                    applicationContext,
//                    R.layout.turma,
//                    turmas
//                )
                listaTurmas.adapter = adapter
            }
        }

    }
}
