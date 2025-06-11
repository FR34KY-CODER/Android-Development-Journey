package com.example.splashscreen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main4)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra(SignInActivity.KEY2)
        val email = intent.getStringExtra(SignInActivity.KEY1)
        val pass = intent.getStringExtra(SignInActivity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val mailText = findViewById<TextView>(R.id.tvEmail)
        val passText = findViewById<TextView>(R.id.tvPass)
        welcomeText.text = "Welcome $name"
        mailText.text = "Email : $email"
        passText.text = "Password : $pass"
    }
}