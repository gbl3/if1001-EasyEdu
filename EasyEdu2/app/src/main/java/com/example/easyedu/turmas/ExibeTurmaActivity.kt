package com.example.easyedu.turmas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.R
import com.example.easyedu.chamada.QRCodeScan
import com.example.easyedu.chamada.geolocalizacao.Transicao
import com.example.easyedu.posts.PostsActivity
import com.example.easyedu.prova.ProvaActivity
import kotlinx.android.synthetic.main.activity_exibe_turma.*

class ExibeTurmaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibe_turma)

        var idTurma: String = intent.getStringExtra("idTurma")
        var nomeTurma: String = intent.getStringExtra("nomeTurma")

        nomeTurmaAtual.text = nomeTurma

        btnVerProvas.setOnClickListener() {
            val newIntent = Intent(this, ProvaActivity::class.java)
            newIntent.putExtra("idTurma", idTurma)
            startActivity(newIntent)
        }

        btnVerPosts.setOnClickListener() {
            val intentPosts = Intent(this, PostsActivity::class.java)
            intentPosts.putExtra("idTurma", idTurma)
            startActivity(intentPosts)
        }

        btn_lerqr.setOnClickListener() {
            val intent = Intent(this, QRCodeScan::class.java)
            startActivity(intent)
        }
        btn_create_qr.setOnClickListener() {
            val intent = Intent(this, Transicao::class.java)

            startActivity(intent)
        }
    }
}
