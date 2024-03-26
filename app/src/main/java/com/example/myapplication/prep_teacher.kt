package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class prep_teacher : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prep_teacher)
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

    fun onClickButtona1(view: View) {
        startActivity(Intent(this, teacher_a1::class.java))
    }

    fun onClickButtona2(view: View) {
        startActivity(Intent(this, teacher_a2::class.java))
    }

    fun onClickButtonb1(view: View) {
        startActivity(Intent(this, teacher_b1::class.java))
    }

    fun onClickButtonb2(view: View) {
        startActivity(Intent(this, teacher_b2::class.java))
    }

    fun onClickButtonBagytova(view: View) {
        startActivity(Intent(this, bagytova::class.java))
    }

    fun onClickButtonLyan(view: View) {
        startActivity(Intent(this, lyan::class.java))
    }

    fun onClickButtonAyap(view: View) {
        startActivity(Intent(this, ayapbergenova::class.java))
    }
}