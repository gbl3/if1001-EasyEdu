package com.example.easyedu.turmas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.R
import com.example.easyedu.prova.AdicionarProvaActivity
import kotlinx.android.synthetic.main.activity_exibe_turma.*

class ExibeTurmaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibe_turma)

        var idTurma: String = intent.getStringExtra("idTurma")
        var nomeTurma: String = intent.getStringExtra("nomeTurma")

        idTurmaAtual.text = idTurma
        nomeTurmaAtual.text = nomeTurma

        btn_nova_prova.setOnClickListener() {
            val intent = Intent(this, AdicionarProvaActivity::class.java)
            intent.putExtra("idTurma", idTurma)
            startActivity(intent)
        }
    }
}
