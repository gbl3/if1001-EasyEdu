package com.example.easyedu.turmas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import kotlinx.android.synthetic.main.activity_turmas.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TurmasActivity : AppCompatActivity() {

    var turmas: Array<Turma>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turmas)
        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val usuarioLogado = db.usuarioAtualDAO().saberPerfilLogado()[0]
            if(usuarioLogado.perfil == 1) {
                layoutTurmas.removeView(findViewById(R.id.btnAddTurma))
            }
        }

        btnAddTurma.setOnClickListener {
            val intent = Intent(this, AdicionarTurmaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val turmas = db.turmasDAO().todasTurmas()
            uiThread {
                val recyclerView = listaTurmasRecyclerView
                recyclerView.adapter = AdapterTurmas(turmas, this@TurmasActivity.applicationContext)

                val layoutManager = LinearLayoutManager(this@TurmasActivity.applicationContext)
                recyclerView.layoutManager = layoutManager
            }
        }

    }
}
