package com.example.easyedu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener(){
            val intent = Intent(this, QRCodeScan::class.java)
            // start your next activity
            startActivity(intent)
        }
        btn_create_qr.setOnClickListener(){
            val intent = Intent(this,QRCodeGenerator::class.java)

            startActivity(intent)
        }
        btn_posts.setOnClickListener(){

        val intent = Intent(this,PostsActivity::class.java)//<<<<<<< HEAD

        startActivity(intent)
        }
        btn_turmas.setOnClickListener(){
            val intent = Intent(this,TurmasActivity::class.java)

            startActivity(intent)
        }
    }
}
