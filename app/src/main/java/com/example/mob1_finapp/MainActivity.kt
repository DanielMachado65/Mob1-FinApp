package com.example.mob1_finapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val operationsList = mutableListOf<FinancialOperation>()
    private val registerOperationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            data?.let {
                val type = it.getStringExtra("type") ?: return@let
                val description = it.getStringExtra("description") ?: return@let
                val value = it.getDoubleExtra("value", 0.0)
                operationsList.add(FinancialOperation(type, description, value))
                Toast.makeText(this, getString(R.string.toast_operation_success), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onClickRegister(view: View) {
        val intent = Intent(this, RegisterOperationActivity::class.java)
        registerOperationLauncher.launch(intent)
    }

    fun onClickStatement(view: View) {
        val intent = Intent(this, StatementActivity::class.java)
        intent.putParcelableArrayListExtra("operations", ArrayList(operationsList))
        startActivity(intent)
    }

    fun onCloseApp(view: View) {
        finish()
    }
}
