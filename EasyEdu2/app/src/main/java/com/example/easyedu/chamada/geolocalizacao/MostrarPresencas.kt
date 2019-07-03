package com.example.easyedu.chamada.geolocalizacao

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.android.synthetic.main.mostrar_presencas.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MostrarPresencas : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrar_presencas)

        val db = EasyEduDB.getDatabase(this)
        doAsync {
            val presencas = db.presencaDAO().todasPresencasview()
            uiThread {
                val adapter = ArrayAdapter<Presenca> (
                    applicationContext,
                    R.layout.content_presenca,
                    presencas
                )
                listaPresenca.setAdapter(adapter)
            }
        }
//        val db = RoomDB.getDatabase(applicationContext)
//        doAsync {
//            val presenca = db.roomDAO().todasPresencas()
//            for (x in presenca){
//                var email = db.roomDAO().todosUsuarios()
//                for (y in email) {
//                    if (y.id == x.idAluno) {
//                        listaPresenca.text = listaPresenca.toString() + y.email + " está presente."
//                    }
//                }
//            }
//            }
    }


}
