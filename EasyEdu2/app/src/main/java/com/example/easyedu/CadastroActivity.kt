package com.example.easyedu


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.easyedu.prova.Questao
import com.example.easyedu.prova.Teste
import com.example.easyedu.prova.TestesGeral
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
            val teste = Teste(t1 = "to entendendo é nada", t2 = 2)
            val questao = Questao(enunciado = "fodase", tipo = 2)
//            Log.d("pedin", usuario.email)
            doAsync {
                var dba = TestesGeral.getDatabase(applicationContext)
                dba.testesDAO().inserirQuestao(questao)
                dba.testesDAO().inserirTeste(teste)
                val db = UsuariosDB.getDatabase(applicationContext)
                val u = dba.testesDAO().tudo()
                db.usuariosDAO().inserirUsuarios(usuario)
 //               val a = db.usuariosDAO().todosUsuarios()
//                val b = db.usuariosDAO().todoscOUNT()
//                Log.d("pedin",b.toString())
//                for (kea in u){
                    Log.d("pedin",u.toString())

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
