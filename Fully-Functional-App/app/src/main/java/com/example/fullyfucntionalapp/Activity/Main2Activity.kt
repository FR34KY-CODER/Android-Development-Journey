package com.example.fullyfucntionalapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullyfucntionalapp.Adapter.TrendsAdapter
import com.example.fullyfucntionalapp.Domain.TrendSDomain
import com.example.fullyfucntionalapp.R

class Main2Activity : AppCompatActivity() {
    private lateinit var adapterTrendsList: TrendsAdapter
    private lateinit var recyclerViewTrends: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        setContentView(R.layout.activity_main)

        initRecyclerView();
        BottomNavigation();
    }

    private fun BottomNavigation(){
        val profileBtn = findViewById<LinearLayout>(R.id.profileBtn)
        profileBtn.setOnClickListener{
            startActivity(Intent(this@Main2Activity, ProfileActivity::class.java))
        }
    }

    private fun initRecyclerView(){
        val items = ArrayList<TrendSDomain>()
        items.add(TrendSDomain("Exploring new Technologies:\nAI, GenAI, Meta", "Machine Learning", "trends"))
        items.add(TrendSDomain("Important points in \nconcluding a work contract", "Work Culture", "trends2"))
        items.add(TrendSDomain("Exploring new Technologies:\nAI, GenAI, Meta", "Machine Learning", "trends"))

        recyclerViewTrends=findViewById(R.id.view1)
        recyclerViewTrends.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterTrendsList = TrendsAdapter(items)
        recyclerViewTrends.adapter= adapterTrendsList
    }
}
