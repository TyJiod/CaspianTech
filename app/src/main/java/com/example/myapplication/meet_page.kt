package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import java.util.Locale

class meet_page : ComponentActivity() {
    private lateinit var  MeetCodeInEditText:EditText
    private lateinit var  MeetEnterButton:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meet_page)

        MeetCodeInEditText = findViewById(R.id.MeetCodeInEditText)
        MeetEnterButton = findViewById(R.id.MeetEnterButton)

        MeetEnterButton.setOnClickListener {
            val code = MeetCodeInEditText.text.toString().trim().lowercase(Locale.getDefault())
            val url = "https://meet.google.com/$code"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

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

    fun onClickButton7(view: View) {
        startActivity(Intent(this, prep_page::class.java))
    }

    fun onClickButton8(view: View) {
        startActivity(Intent(this, user_page::class.java))
    }
}