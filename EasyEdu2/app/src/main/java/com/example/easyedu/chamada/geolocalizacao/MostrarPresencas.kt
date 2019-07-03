package com.example.easyedu.chamada.geolocalizacao

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.R
import com.example.easyedu.database.RoomDB
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.android.synthetic.main.mostrar_presencas.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MostrarPresencas : AppCompatActivity() {

    var presenca: Array<Presenca>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrar_presencas)


    }

    override fun onResume() {
        super.onResume()
        val db = RoomDB.getDatabase(applicationContext)
        doAsync {
            val presenca = db.roomDAO().todasPresencas()
            uiThread {
                val adapter = ArrayAdapter<Presenca> (
                    applicationContext,
                    R.layout.mostrar_presencas,
                    presenca
                )
                listaPresenca.setAdapter(adapter)
            }
        }

    }


}