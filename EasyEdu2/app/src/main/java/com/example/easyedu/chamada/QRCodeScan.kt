package com.example.easyedu.chamada

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qrcode_scan.*
import android.widget.Toast
import com.example.easyedu.R


class QRCodeScan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_scan)

        btn_scan.setOnClickListener(){
            val scanner = IntentIntegrator(this)
            scanner.initiateScan()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Leitura realizada com sucesso! " + result.contents, Toast.LENGTH_LONG).show()
                    val intent = Intent(this, finalChamada::class.java)
                    intent.putExtra("codigo", result.contents)
                    startActivity(intent)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
