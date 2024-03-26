package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class HomePage : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
    }

    fun onClickButton2(view: View) {
        startActivity(Intent(this, user_notify::class.java))
    }

    fun onClickButton3(view: View) {
        startActivity(Intent(this, user_page::class.java))
    }

    fun onClickButton4(view: View) {
        startActivity(Intent(this, HomePage::class.java))
    }

    fun onClickButton5(view: View) {
        startActivity(Intent(this, document_page::class.java))
    }

    fun onClickButton6(view: View) {
        startActivity(Intent(this, meet_page::class.java))
    }

    fun onClickButton7(view: View) {
        startActivity(Intent(this, prep_page::class.java))
    }

    fun onClickButton8(view: View) {
        startActivity(Intent(this, user_page::class.java))
    }

    fun onClickButton9(view: View) {
        startActivity(Intent(this, article_1::class.java))
    }

    fun onClickButton10(view: View) {
        startActivity(Intent(this, article_2::class.java))
    }

    fun onClickButtonDoc(view: View) {
        startActivity(Intent(this, document_page::class.java))
    }
}


