package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_page)

        val button: Button = findViewById(R.id.getStartedButton)
            button.setOnClickListener{
                val intent = Intent(this, LogInPage::class.java)
                startActivity(intent)
            }
    }
}
