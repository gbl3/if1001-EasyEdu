package com.example.easyedu.prova.questoes

import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import android.widget.*
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import com.example.easyedu.EasyEduDB
import com.example.easyedu.R
import kotlinx.android.synthetic.main.activity_adicionar_questao.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class InserirQuestaoActivity : AppCompatActivity() {

    val alternativas: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_questao)

        btnAddQuestao.setOnClickListener{
//            if (btnQuestaoFechada.isChecked) {
//                setAlternativas()
//                TODO("Precisa complementar")
//            } else
                if (btnQuestaoAberta.isChecked) {
                var idProva: String = intent.getStringExtra("idProva")
                val enunciadoQuestao = enunciadoQuestao.text.toString()
                val questao = Questao(0,
                    enunciado = enunciadoQuestao,
                    tipo = 1,
                    idProva = idProva.toInt()
                )

                doAsync {
                    val db = EasyEduDB.getDatabase(applicationContext)
                    db.questoesDAO().inserirQuestao(questao)
                    uiThread {
                        finish()
                    }
                }
            }
        }

    }

    fun tipoQuestaoClicked(view: View){
        val radio: RadioButton = findViewById(selectTipoQuestao.checkedRadioButtonId)
        var count = 1
        if (radio.text.toString() == getString(R.string.questaoFechada)) {
            val inserirAlternativaButton = Button(this)
            var buttonParams = LayoutParams(
                LayoutParams.MATCH_PARENT, // width
                LayoutParams.WRAP_CONTENT // height
            )
            inserirAlternativaButton.layoutParams = buttonParams
            inserirAlternativaButton.text = "Inserir alternativa"
            inserirAlternativaButton.id = R.id.botaoNovaAlternativa

            conteudoQuestao.addView(inserirAlternativaButton)

            inserirAlternativaButton.setOnClickListener{
                var labelTextoQuestao = TextView(this)
                labelTextoQuestao.text = "Alternativa ".plus(count)
                labelTextoQuestao.setTextSize(COMPLEX_UNIT_SP, 20F)

                var inputTextoQuestao = EditText(this)
                var layout = findViewById<LinearLayout>(R.id.conteudoQuestao)
                layout.addView(labelTextoQuestao)
                layout.addView(inputTextoQuestao)
                count += 1
            }
        }
    }

    fun setAlternativas() {
        var qtdChild = conteudoQuestao.childCount

        for (posicao in 0..qtdChild) {
            var elemento = conteudoQuestao.getChildAt(posicao)
            if (elemento is EditText){
                alternativas.add(elemento.text.toString())
            }
        }
    }
}
