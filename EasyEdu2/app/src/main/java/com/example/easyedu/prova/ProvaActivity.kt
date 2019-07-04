package com.example.easyedu.prova

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import kotlinx.android.synthetic.main.activity_prova.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ProvaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prova)

        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val usuarioLogado = db.usuarioAtualDAO().saberPerfilLogado()[0]
            if(usuarioLogado.perfil == 1) {
                layoutProva.removeView(findViewById(R.id.btnCriarProva))
            }
        }

        btnCriarProva.setOnClickListener {
            val newIntent = Intent(this, AdicionarProvaActivity::class.java)
            newIntent.putExtra("idTurma", intent.getStringExtra("idTurma"))
            startActivity(newIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val idTurma = intent.getStringExtra("idTurma")
            val provas = db.provasDao().buscaProvaPeloIdDaTurma(idTurma.toInt())
            uiThread {
                val recyclerView = listaProvasRecyclerView
                recyclerView.adapter = AdapterProvas(provas, this@ProvaActivity.applicationContext)

                val layoutManager = LinearLayoutManager(this@ProvaActivity.applicationContext)
                recyclerView.layoutManager = layoutManager
            }
        }
    }
}
