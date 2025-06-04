package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.PackageManager
import android.Manifest
import android.content.Context
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val webButton= findViewById<CardView>(R.id.cardWeb)
        val cameraButton= findViewById<CardView>(R.id.cardCamera)
        val callButton= findViewById<CardView>(R.id.cardCall)

        webButton.setOnClickListener {
            val intent= Intent(Intent.ACTION_VIEW)
            val uriString= "https://www.youtube.com/"
            intent.data= parse(uriString)
            startActivity(intent)
        }

        cameraButton.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        callButton.setOnClickListener {
            val phoneNumber = "941-338-5933"
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(callIntent)
        }

    }
}

