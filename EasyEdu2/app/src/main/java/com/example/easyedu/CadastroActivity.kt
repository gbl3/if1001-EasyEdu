package com.example.easyedu

import android.content.Intent
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_turmas.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CadastroActivity : AppCompatActivity() {
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

            val usuario = Usuario(email = userEmail,senha =  userSenha, perfil = userPerfil)
            doAsync {
                val db = UsuariosDB.getDatabase(applicationContext)
                db.usuariosDAO().inserirUsuarios(usuario)
                uiThread {
                    finish()

                }

            }
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,LoginActivity::class.java)

            startActivity(intent)
//            val db = UsuariosDB.getDatabase(this)
//            doAsync {
//                val usuarios = db.usuariosDAO().todosUsuarios()
//                uiThread {
//                    val adapter = ArrayAdapter<Usuario> (
//                        applicationContext,
//                        R.layout.turma,
//                        usuarios
//                    )
//                    listaTurmas.setAdapter(adapter)
//                }
//            }
        }
    }

}
