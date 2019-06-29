package com.example.easyedu.autenticacao


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.easyedu.R
import com.example.easyedu.Usuario
import com.example.easyedu.UsuariosDB
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.jetbrains.anko.doAsync

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

            val usuario = Usuario(email = userEmail, senha = userSenha, perfil = userPerfil)
//            Log.d("pedin", usuario.email)
            doAsync {

                val db = UsuariosDB.getDatabase(applicationContext)
                db.usuariosDAO().inserirUsuarios(usuario)
 //               val a = db.usuariosDAO().todosUsuarios()
//                val b = db.usuariosDAO().todoscOUNT()
//                Log.d("pedin",b.toString())
//                for (kea in u){
//                    Log.d("pedin",u.toString())

//                }

            }
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }
        btn_back.setOnClickListener(){
            finish()
        }
    }

}
