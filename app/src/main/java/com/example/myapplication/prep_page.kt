package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class prep_page : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prep_page)
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

    fun onClickButton8(view: View) {
        startActivity(Intent(this, user_page::class.java))
    }

    fun onClickButtonFirst(view: View) {
        startActivity(Intent(this, prep_calendar::class.java))
    }

    fun onClickButtonSecond(view: View) {
        startActivity(Intent(this, prep_teacher::class.java))
    }

    fun onClickButtonThird(view: View) {
        startActivity(Intent(this, prep_format::class.java))
    }
}