package com.example.easyedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_back.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}
