package com.example.myapplication

import UserPhotoUtil
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class prep_page : ComponentActivity() {
    private lateinit var profileImageButton: ImageButton
    private lateinit var welcomeUserTextView: TextView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prep_page)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        currentUser = firebaseAuth.currentUser!!
        databaseReference = database.reference.child("users").child(currentUser.uid)

        profileImageButton = findViewById(R.id.profileImageButton)
        val userPhotoUtil = UserPhotoUtil()

        userPhotoUtil.setDatabaseReference(databaseReference)

        userPhotoUtil.loadUserPhoto(profileImageButton)

        welcomeUserTextView = findViewById(R.id.welcomeUserTextView)

        val userDisplayName = currentUser.displayName
        if (userDisplayName != null) {
            welcomeUserTextView.text = "Hi, $userDisplayName!"
        } else {
            welcomeUserTextView.text = "Hi, user!"
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