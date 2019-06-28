package com.example.easyedu


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
//            Log.d("pedin", usuario.email)
            doAsync {
                val db = UsuariosDB.getDatabase(applicationContext)
                db.usuariosDAO().inserirUsuarios(usuario)
//                val a = db.usuariosDAO().todosUsuarios()
//                val b = db.usuariosDAO().todoscOUNT()
//                Log.d("pedin",b.toString())
//                for (kea in a){
//                    Log.d("pedin",kea.email)
//
//                }

            }
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

}
