package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class LogInPage : ComponentActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_page)

        emailEditText = findViewById(R.id.EmailInput)
        passwordEditText = findViewById(R.id.PasswordInput)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun onClickButton1(view: View) {
        startActivity(Intent(this, HomePage::class.java))
    }

    fun goToSignPage(view: View?) {
        val intent = Intent(this, SignInPage::class.java)
        startActivity(intent)
    }

    fun signIn(view: View) {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Perform basic validation
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        // Sign in the user with email and password
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to the home page
                    startActivity(Intent(this, HomePage::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
