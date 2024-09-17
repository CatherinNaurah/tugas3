package com.example.tugas3

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        // Terima data dari Intent
        val result = intent.getDoubleExtra("RESULT", 0.0)
        val name = intent.getStringExtra("NAME")
        val nim = intent.getStringExtra("NIM")

        // Tampilkan hasil, nama, dan NIM
        findViewById<TextView>(R.id.textViewResult).text = "Hasil: $result"
        findViewById<TextView>(R.id.textView).text = name
        findViewById<TextView>(R.id.textView2).text = nim

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
