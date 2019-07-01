package com.example.easyedu.prova.questoes

import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout.LayoutParams
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.R
import kotlinx.android.synthetic.main.activity_adicionar_questao.*

class InserirQuestaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_questao)

        btnAddQuestao.setOnClickListener{
            TODO("Falta implementar")
        }

    }

    fun tipoQuestaoClicked(view: View){
        val radio: RadioButton = findViewById(selectTipoQuestao.checkedRadioButtonId)
        if (radio.text.toString() == getString(R.string.questaoAberta)) {
            TODO("Precisa implementar ainda")
        } else {
            val labelTextoQuestao = TextView(this)
            var labelParams : LayoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            labelTextoQuestao.text = "Conteúdo da Questão:"
            labelTextoQuestao.setTextSize(COMPLEX_UNIT_SP, 30F)

            val inputTextoQuestao = EditText(this)
            var inputParams : LayoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            inputTextoQuestao.id = R.id.inputTextoQuestaoAberta

            conteudoQuestao.addView(labelTextoQuestao)
            conteudoQuestao.addView(inputTextoQuestao)
        }

    }

}
