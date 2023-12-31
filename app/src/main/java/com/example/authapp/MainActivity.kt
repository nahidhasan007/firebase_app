package com.example.authapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seeAllBtn = findViewById<TextView>(R.id.users_all)
        seeAllBtn.setOnClickListener {
            val intent = Intent(this, Users::class.java)
            startActivity(intent)
        }
    }
}