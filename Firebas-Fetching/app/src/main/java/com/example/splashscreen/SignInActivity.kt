package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    companion object{
        const val KEY1  = "com.example.splashscreen.SignInActivity.mail"
        const val KEY2  = "com.example.splashscreen.SignInActivity.name"
        const val KEY3  = "com.example.splashscreen.SignInActivity.pass"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.etName1)

        signInButton.setOnClickListener {
            // Take ref till "Users" node from Firebase Database!

            val userNameString = userName.text.toString()
            if(userNameString.isNotEmpty()){
                readData(userNameString)
            }
            else{
                Toast.makeText(this,"Please Enter User Name",Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<TextView>(R.id.tvForgot).setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    private fun readData(userNameString: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(userNameString).get().addOnSuccessListener { //This time we added .get() to read data
            //User Exist or Not?
            if(it.exists()) {
                //Welcome User
                val email = it.child("email").value
                val name = it.child("name").value
                val password = it.child("password").value

                val intentWelcome = Intent(this, ProfileActivity::class.java)
                intentWelcome.putExtra(KEY1, email.toString())
                intentWelcome.putExtra(KEY2, name.toString())
                intentWelcome.putExtra(KEY3, password.toString())
                startActivity(intentWelcome)
            }
            else{
                Toast.makeText(this,"User doesn't exist Sign Up first!",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{//Failure of Operation
            Toast.makeText(this,"Error202 : Error in Fetching",Toast.LENGTH_SHORT).show()
        }
    }
}