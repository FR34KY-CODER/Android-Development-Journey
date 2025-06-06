package com.example.multiscreenapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY = "com.example.multiscreenapp.MainActivity.KEY"
    }

    private lateinit var btnOrder: Button
    private lateinit var eT1: EditText
    private lateinit var eT2: EditText
    private lateinit var eT3: EditText
    private lateinit var eT4: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnOrder = findViewById(R.id.btnOrder)
        eT1 = findViewById(R.id.eT1)
        eT2 = findViewById(R.id.eT2)
        eT3 = findViewById(R.id.eT3)
        eT4 = findViewById(R.id.eT4)

        btnOrder.setOnClickListener {
            val orderList = listOf(
                eT1.text?.toString()?.trim(),
                eT2.text?.toString()?.trim(),
                eT3.text?.toString()?.trim(),
                eT4.text?.toString()?.trim()
            ).filter { !it.isNullOrBlank() }.joinToString(" ")

            val intent = Intent(this, ShowOrder::class.java)
            intent.putExtra(KEY, orderList)
            startActivity(intent)
        }
    }
}