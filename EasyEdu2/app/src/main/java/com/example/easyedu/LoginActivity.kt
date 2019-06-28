package com.example.easyedu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.users.UsuarioAtual
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener(){
            val emailUser = username.text.toString()
            val senhaUser = password.text.toString()
            doAsync {
                val db = UsuariosDB.getDatabase(applicationContext)
                val dba = UsuarioAtualDB.getDatabase(applicationContext)
                val usrExist = db.usuariosDAO().validaLogin(emailUser)
                val passExist = db.usuariosDAO().validaSenha(emailUser)
                val usrAtual = UsuarioAtual(id = 0, email = emailUser, perfil = 2)
                if (usrExist == 1 && passExist == senhaUser){
                    dba.usuarioAtualDAO().inserirAtual(usrAtual)
                    uiThread {
                        Toast.makeText(this@LoginActivity, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                        if (usrAtual.perfil == 2) {
                            val intent = Intent(this@LoginActivity, PostsActivity::class.java)
                            startActivity(intent)
                        }else{
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                        }

                        else {
                    uiThread {
                        Toast.makeText(this@LoginActivity, "Login ou Senha Inválido!", Toast.LENGTH_SHORT).show()
                    }
                }



            }

        }
        back_voltar.setOnClickListener(){
            val intent = Intent(this@LoginActivity, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}