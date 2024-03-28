package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
class SignInPage : ComponentActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_page)

        nameEditText = findViewById(R.id.NameInput)
        emailEditText = findViewById(R.id.EmailInput)
        passwordEditText = findViewById(R.id.PasswordInput)

        signInButton = findViewById(R.id.SignUpButton)

        signInButton.setOnClickListener{

            firebaseAuth = FirebaseAuth.getInstance()
            val databaseRef = FirebaseDatabase.getInstance().getReference("users")

        val email = emailEditText .text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val name = nameEditText.text.toString().trim()

            if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                Toast.makeText(this, "fill the fields", Toast.LENGTH_SHORT).show()
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = firebaseAuth.currentUser

                            val newUser = User(
                                email = email,
                                name = name
                            )
                            user?.uid?.let { userId ->
                                databaseRef.child(userId).setValue(newUser)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Data saved Successfully", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener{ e ->
                                        Toast.makeText(this, "Could not save data: ${e.message}", Toast.LENGTH_SHORT).show()
                                    }
                            }
                            startActivity(Intent(this, HomePage::class.java))
                        } else {
                            // Выводим текст ошибки, если регистрация не удалась
                            Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }
    }

    fun goToLogInPage(view: View) {
        startActivity(Intent(this, LogInPage::class.java))
    }
}
