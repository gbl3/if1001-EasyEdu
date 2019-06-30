package com.example.easyedu.autenticacao


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.easyedu.R
import com.example.easyedu.Usuario
import com.example.easyedu.UsuariosDB
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_cadastro.EmailInput
import kotlinx.android.synthetic.main.activity_cadastro.SenhaInput
import kotlinx.android.synthetic.main.activity_cadastro.btnAluno
import kotlinx.android.synthetic.main.activity_cadastro.btnProfessor
import kotlinx.android.synthetic.main.activity_cadastro_new.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_new)

        btn_back.setOnClickListener(){
            finish()
        }

        btn_cadastro.setOnClickListener() {
            val userEmail = EmailInput.text.toString()
            val userSenha = SenhaInput.text.toString()
            val userConf = conf_senha.text.toString()
            var userPerfil = 0
            if (btnAluno.isChecked) {
                userPerfil = 1
                btnProfessor.isChecked = false
            }
            if (btnProfessor.isChecked) {
                userPerfil = 2
                btnAluno.isChecked = false
            }

            if (userConf == userSenha) {
                val usuario = Usuario(email = userEmail, senha = userSenha, perfil = userPerfil)
//            Log.d("pedin", usuario.email)
                doAsync {
                    val db = UsuariosDB.getDatabase(applicationContext)
                    db.usuariosDAO().inserirUsuarios(usuario)
//                    val todos = db.usuariosDAO().todosUsuarios()
//                    for (um in todos) {
//                        if (um.email == userEmail) {
//                            uiThread {
//                                Toast.makeText(this@CadastroActivity, "Usuário já existe!", Toast.LENGTH_SHORT).show()
//                                finish()
//                            }
//                        }
//
//                    }
                    //               val a = db.usuariosDAO().todosUsuarios()
//                val b = db.usuariosDAO().todoscOUNT()
//                Log.d("pedin",b.toString())
//                for (kea in u){
//                    Log.d("pedin",u.toString())

//                }

                }
                Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "A confirmação de senha deve ser igual a senha escolhida!", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
