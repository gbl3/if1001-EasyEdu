package com.example.easyedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.easyedu.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_qrcode_generator.*

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
        btn_perfil.setOnClickListener(){
<<<<<<< HEAD
            val intent = Intent(this, UserProfileActivity::class.java)
=======
            val intent = Intent(this,UserProfileActivity::class.java)
>>>>>>> 00115c4abec03f74ebc0278626c9da02a4a09e36

            startActivity(intent)
        }
    }
}
