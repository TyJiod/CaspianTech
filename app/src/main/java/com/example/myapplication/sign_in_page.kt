package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity

class sign_in_page : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_page)
    }

    fun goToLogInPage(view: View) {
        startActivity(Intent(this, LogInPage::class.java))
    }

    fun onClickButton1(view: View?) {
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }

}
