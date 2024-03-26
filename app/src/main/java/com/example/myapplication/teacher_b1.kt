package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class teacher_b1 : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_b1)
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

    fun onClickButtonCancel(view: View) {
        startActivity(Intent(this, prep_teacher::class.java))
    }

    fun onClickButtonAyap(view: View) {
        startActivity(Intent(this, ayapbergenova::class.java))
    }

    fun onClickButtonNauryz(view: View) {
        startActivity(Intent(this, nauryzbekova::class.java))
    }

    fun onClickButtonErzhan(view: View) {
        startActivity(Intent(this, yerzhanova::class.java))
    }
}