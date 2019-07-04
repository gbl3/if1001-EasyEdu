package com.example.easyedu.chamada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.easyedu.R
import com.example.easyedu.chamada.geolocalizacao.LocalActivity
import com.example.easyedu.chamada.geolocalizacao.Transicao
import kotlinx.android.synthetic.main.activity_final_chamada.*

class finalChamada : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_chamada)

        val codigo = intent.getStringExtra("codigo")
        textCode.text = codigo

        btn_go.setOnClickListener(){
            val intent = Intent(this, LocalActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Iniciando confirmação de localização...", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
