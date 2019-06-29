package com.example.easyedu.autenticacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.easyedu.R
import com.example.easyedu.Usuario
import com.example.easyedu.UsuariosDB
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class InserirUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_cadastro.setOnClickListener() {
            val userEmail = EmailInput.text.toString()
            val userSenha = SenhaInput.text.toString()
            var userPerfil = 0
            if (btnAluno.isChecked){
                userPerfil = 1
                btnProfessor.isChecked = false
            }
            if (btnProfessor.isChecked){
                userPerfil = 2
                btnAluno.isChecked = false
            }

            val usuario = Usuario(email = userEmail, senha = userSenha, perfil = userPerfil)
            doAsync {
                val db = UsuariosDB.getDatabase(applicationContext)
                db.usuariosDAO().inserirUsuarios(usuario)
                uiThread {
                    finish()

                }

            }
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()

        }
    }

}
