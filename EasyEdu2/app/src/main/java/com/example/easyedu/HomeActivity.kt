package com.example.easyedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.easyedu.autenticacao.LoginActivity
import com.example.easyedu.posts.PostsActivity
import com.example.easyedu.turmas.TurmasActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.doAsync

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_start.setOnClickListener() {
            doAsync {
                val db = UsuarioAtualDB.getDatabase(applicationContext)
                val perfil = db.usuarioAtualDAO().saberPerfilLogado()
                val existe = db.usuarioAtualDAO().saberSeExiste()
                for(p in perfil){
                    Log.d("pedin",p.perfil.toString())
                    if(p.perfil.toString() == "1"){
                        val intent = Intent(this@HomeActivity, TurmasActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    if(p.perfil.toString() == "2") {
                        val intent = Intent(this@HomeActivity, PostsActivity::class.java)
                        startActivity(intent)
                        finish()
                    }


                }

                if(existe == 0){
                    val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

        }
    }
}
