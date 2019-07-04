package com.example.easyedu.perfil

import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import com.example.easyedu.autenticacao.LoginActivity
import com.example.easyedu.chamada.QRCodeGenerator
import com.example.easyedu.chamada.QRCodeScan
import com.example.easyedu.chamada.geolocalizacao.LocalActivity
import com.example.easyedu.chamada.geolocalizacao.MostrarPresencas
import com.example.easyedu.chamada.geolocalizacao.Transicao
import com.example.easyedu.posts.AdicionarPostActivity
import com.example.easyedu.posts.PostsActivity
import com.example.easyedu.turmas.AdapterTurmas
import com.example.easyedu.turmas.ExibeTurmaActivity
import com.example.easyedu.turmas.TurmasActivity
import kotlinx.android.synthetic.main.activity_perfil.*
import org.jetbrains.anko.doAsync

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)


        geo.setOnClickListener(){
            val intent = Intent(this,MostrarPresencas::class.java)
            startActivity(intent)
        }


        btn_turmas.setOnClickListener() {
            val intent = Intent(this, TurmasActivity::class.java)

            startActivity(intent)
        }

        btn_logout.setOnClickListener(){
            doAsync {
                val db = EasyEduDB.getDatabase(applicationContext)
                val tudo = db.usuarioAtualDAO().saberPerfilLogado()
                for(atual in tudo) {
                    db.usuarioAtualDAO().removerAtual(atual)
                }
                val intent = Intent(this@PerfilActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        var builder = AlertDialog.Builder(this@PerfilActivity)
//
//        builder.setTitle("LOGOUT")
//        builder.setMessage("VOCê TEM CERTEZA QUE QUER FAZER LOGOUT?")
//        builder.setPositiveButton("Sim"){dialog, which ->
//            Toast.makeText(applicationContext,"Ok, we change the app background.",Toast.LENGTH_SHORT).show()
//
//
//        }
//    }
}
