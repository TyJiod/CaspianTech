package com.example.myapplication

import UserPhotoUtil
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale

class meet_page : ComponentActivity() {
    private lateinit var  MeetCodeInEditText:EditText
    private lateinit var  MeetEnterButton:Button

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    private lateinit var profileImageButton: ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.meet_page)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        currentUser = firebaseAuth.currentUser!!
        databaseReference = database.reference.child("users").child(currentUser.uid)

        profileImageButton = findViewById(R.id.profileImageButton)
        val userPhotoUtil = UserPhotoUtil()

        userPhotoUtil.setDatabaseReference(databaseReference)

        userPhotoUtil.loadUserPhoto(profileImageButton)

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