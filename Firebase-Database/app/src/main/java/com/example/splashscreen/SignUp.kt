package com.example.splashscreen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<EditText>(R.id.etName)
        val etMail = findViewById<EditText>(R.id.etMail)
        val etPass = findViewById<EditText>(R.id.etPass)

        signButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val pass = etPass.text.toString()


            val user = User(name, mail, pass)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(name).setValue(user).addOnSuccessListener {
                etName.text.clear()
                etMail.text.clear()
                etPass.text.clear()
                Toast.makeText(this,"User Registered ✅",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed ❌",Toast.LENGTH_SHORT).show()
            }
        }
    }
}