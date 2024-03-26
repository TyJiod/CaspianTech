package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity

class LogInPage : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_page)
    }
        fun onClickButton1(view: View) {
            startActivity(Intent(this, HomePage::class.java))
        }

        fun goToSignPage(view: View?) {
            val intent = Intent(this, sign_in_page::class.java)
            startActivity(intent)
        }

}