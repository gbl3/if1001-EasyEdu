package com.example.easyedu.autenticacao

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import com.example.easyedu.perfil.PerfilActivity
import com.example.easyedu.users.UsuarioAtual
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)

        login.setOnClickListener(){
            val emailUser = username.text.toString()
            val senhaUser = password.text.toString()
            doAsync {
                val db = EasyEduDB.getDatabase(applicationContext)
                val usrExist = db.usuarioDAO().validaLogin(emailUser)
                val passExist = db.usuarioDAO().validaSenha(emailUser)
                val usrAtual = UsuarioAtual(id = usrExist.id, email = emailUser, perfil = usrExist.perfil, userId = usrExist.id)
                if (usrExist != null && passExist == senhaUser){
                    db.usuarioAtualDAO().inserirAtual(usrAtual)
                    uiThread {
                        Toast.makeText(this@LoginActivity, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                        if (usrAtual.perfil == 2) {
                            val intent = Intent(this@LoginActivity, PerfilActivity::class.java)
                            startActivity(intent)
                        }else{
                            val intent = Intent(this@LoginActivity, PerfilActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    finish()
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