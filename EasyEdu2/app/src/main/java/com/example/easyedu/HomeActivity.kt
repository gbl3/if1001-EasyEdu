package com.example.easyedu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.autenticacao.LoginActivity
import com.example.easyedu.perfil.PerfilActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.doAsync

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_start.setOnClickListener() {
            doAsync {
                val db = EasyEduDB.getDatabase(applicationContext)
                val perfil = db.usuarioAtualDAO().saberPerfilLogado()
                val existe = db.usuarioAtualDAO().saberSeExiste()
                for(p in perfil){
                    if(p.perfil.toString() == "1"){
                        val intent = Intent(this@HomeActivity, PerfilActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    if(p.perfil.toString() == "2") {
                        val intent = Intent(this@HomeActivity, PerfilActivity::class.java)
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
