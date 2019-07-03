package com.example.easyedu.autenticacao

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import com.example.easyedu.Usuario
import kotlinx.android.synthetic.main.activity_cadastro.EmailInput
import kotlinx.android.synthetic.main.activity_cadastro.SenhaInput
import kotlinx.android.synthetic.main.activity_cadastro.btnAluno
import kotlinx.android.synthetic.main.activity_cadastro.btnProfessor
import kotlinx.android.synthetic.main.activity_cadastro_new.*
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
                val db = EasyEduDB.getDatabase(applicationContext)
                db.usuarioDAO().inserirUsuarios(usuario)
                uiThread {
                    finish()
                }

            }
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()

        }
    }

}
