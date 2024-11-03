package com.example.mob1_finapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterOperationActivity : AppCompatActivity() {

    private lateinit var descriptionEditText: EditText
    private lateinit var valueEditText: EditText
    private lateinit var debitRadioButton: RadioButton
    private lateinit var creditRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_operation)

        descriptionEditText = findViewById(R.id.descriptionEditText)
        valueEditText = findViewById(R.id.valueEditText)
        debitRadioButton = findViewById(R.id.debitRadioButton)
        creditRadioButton = findViewById(R.id.creditRadioButton)
    }
    fun onSaveOperation(view: View) {
        val description = descriptionEditText.text.toString()
        val value = valueEditText.text.toString().toDoubleOrNull()
        val isDebit = debitRadioButton.isChecked

        // Verifica se a descrição é uma string válida (apenas letras e espaços)
        if (description.isEmpty() || !description.matches(Regex("^[A-Za-zÀ-ÿ ]+$"))) {
            val toast = Toast.makeText(this, "A descrição deve conter apenas letras.", Toast.LENGTH_SHORT)
            toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 50)
            toast.show()
            return
        }

        if (value == null) {
            val toast = Toast.makeText(this, "Por favor, insira um valor numérico válido.", Toast.LENGTH_SHORT)
            toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 50)
            toast.show()
            return
        }

        val type = if (isDebit) "Débito" else "Crédito"

        val resultIntent = Intent()
        resultIntent.putExtra("type", type)
        resultIntent.putExtra("description", description)
        resultIntent.putExtra("value", value)
        setResult(RESULT_OK, resultIntent)

        finish()
    }

}
