package com.example.easyedu.chamada.geolocalizacao

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
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
        val db = EasyEduDB.getDatabase(applicationContext)
        doAsync {
            val presenca = db.presencaDAO().todasPresencas()
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