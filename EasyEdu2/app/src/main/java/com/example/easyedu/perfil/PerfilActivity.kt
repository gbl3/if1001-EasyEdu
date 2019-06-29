package com.example.easyedu.perfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.easyedu.R
import com.example.easyedu.UsuarioAtualDB
import com.example.easyedu.autenticacao.LoginActivity
import com.example.easyedu.users.UsuarioAtual
import kotlinx.android.synthetic.main.activity_perfil.*
import org.jetbrains.anko.doAsync

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        btn_logout.setOnClickListener(){
            doAsync {
                val db = UsuarioAtualDB.getDatabase(applicationContext)
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
