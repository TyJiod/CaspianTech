package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class LogInPage : ComponentActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var logInButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_page)

        emailEditText = findViewById(R.id.EmailInput)
        passwordEditText = findViewById(R.id.PasswordInput)
        firebaseAuth = FirebaseAuth.getInstance()

        logInButton = findViewById(R.id.logInButton)

        logInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform basic validation
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, HomePage::class.java))
                    } else {
                        Toast.makeText(
                            baseContext, "Authentication failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this,"something went wrong try again", Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun onClickButton1(view: View) {
        startActivity(Intent(this, HomePage::class.java))
    }

    fun goToSignPage(view: View?) {
        val intent = Intent(this, SignInPage::class.java)
        startActivity(intent)
    }
}

