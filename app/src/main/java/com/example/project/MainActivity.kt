package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a = findViewById<ImageView>(R.id.imageView)
        a.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
        }
    }
}