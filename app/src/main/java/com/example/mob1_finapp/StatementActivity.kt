package com.example.mob1_finapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StatementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        val operationsListView: ListView = findViewById(R.id.statementListView)
        val emptyMessageTextView: TextView = findViewById(R.id.emptyMessageTextView)

        val operations = intent.getParcelableArrayListExtra<FinancialOperation>("operations") ?: arrayListOf()

        if (operations.isEmpty()) {
            emptyMessageTextView.text = "Nenhuma operação registrada."
            emptyMessageTextView.visibility = View.VISIBLE
            operationsListView.visibility = View.GONE
        } else {
            val operationsDisplay = operations.map { "${it.type} - ${it.description} - R$ ${it.value}" }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, operationsDisplay)
            operationsListView.adapter = adapter
            emptyMessageTextView.visibility = View.GONE
        }
    }
}
