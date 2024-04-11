package com.example.myapplication

import UserPhotoUtil
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomePage : ComponentActivity(){

    private lateinit var welcomeBackTextView: TextView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    private lateinit var profileImageButton: ImageButton
    private lateinit var interviewDateInfo: TextView
    private lateinit var interviewTimeInfo: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        profileImageButton = findViewById(R.id.profileImageButton)

        welcomeBackTextView = findViewById(R.id.welcomeBackTextView)
        interviewDateInfo = findViewById(R.id.interviewDateInfo)
        interviewTimeInfo = findViewById(R.id.interviewTimeInfo)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        currentUser = firebaseAuth.currentUser!!
        databaseReference = database.reference.child("users").child(currentUser.uid)

        val userPhotoUtil = UserPhotoUtil()

        userPhotoUtil.setDatabaseReference(databaseReference)

        userPhotoUtil.loadUserPhoto(profileImageButton)

        databaseReference.child("userInterviewData").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val interviewDate = snapshot.child("userInterviewDate").getValue(String::class.java)
                    val interviewTime = snapshot.child("userInterviewTime").getValue(String::class.java)

                    if (interviewDate != null && interviewTime != null) {
                        interviewDateInfo.text = "Intervew is due to, $interviewDate"
                        interviewTimeInfo.text = "at $interviewTime"
                    } else {
                        interviewDateInfo.text = "No interview scheduled"
                        interviewTimeInfo.text = ""
                    }
                } else {
                    interviewDateInfo.text = "No interview scheduled"
                    interviewTimeInfo.text = ""
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибок при чтении данных из базы данных
                interviewDateInfo.text = "Error loading interview info"
                interviewTimeInfo.text = ""
            }
        })

        // Отображение приветственного сообщения
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userName = snapshot.child("name").getValue(String::class.java)
                val welcomeMessage = "Welcome back, ${userName ?: "User"}"
                welcomeBackTextView.text = welcomeMessage
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибок при чтении данных из базы данных
            }
        })
    }

    fun Notification(view: View) {
        startActivity(Intent(this, user_notify::class.java))
    }

    fun ProfileIcon(view: View) {
        startActivity(Intent(this, user_page::class.java))
    }

    fun DocumentPage(view: View) {
        startActivity(Intent(this, document_page::class.java))
    }

    fun MeetPage(view: View) {
        startActivity(Intent(this, meet_page::class.java))
    }

    fun PrepPage(view: View) {
        startActivity(Intent(this, prep_page::class.java))
    }

    fun UserPage(view: View) {
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


