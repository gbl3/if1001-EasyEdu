package com.example.easyedu.prova

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import com.example.easyedu.prova.questoes.AdapterQuestoes
import com.example.easyedu.prova.questoes.InserirQuestaoActivity
import kotlinx.android.synthetic.main.activity_exibe_prova.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ExibeProvaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibe_prova)

        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val usuarioLogado = db.usuarioAtualDAO().saberPerfilLogado()[0]
            if(usuarioLogado.perfil == 1) {
                layoutExibeProva.removeView(findViewById(R.id.btnInserirQuestao))
            }
        }

        var tituloProva: String = intent.getStringExtra("tituloProva")
        tituloProvaAtual.text = tituloProva

        var idProva: String = intent.getStringExtra("idProva")
        btnInserirQuestao.setOnClickListener {
            val newIntent = Intent(this, InserirQuestaoActivity::class.java)
            newIntent.putExtra("idProva", idProva)
            startActivity(newIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        var idProva: String = intent.getStringExtra("idProva")
        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val questoes = db.questoesDAO().buscaQuestaoPeloIdDaProva(idProva.toInt())
            uiThread {
                val recyclerView = listaQuestoesRecyclerView
                recyclerView.adapter = AdapterQuestoes(questoes, this@ExibeProvaActivity.applicationContext)

                val layoutManager = LinearLayoutManager(this@ExibeProvaActivity.applicationContext)
                recyclerView.layoutManager = layoutManager
            }
        }
    }
}
