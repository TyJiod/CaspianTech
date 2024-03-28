package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class user_page : ComponentActivity() {

    private lateinit var userNameTextView: TextView
    private lateinit var userProfilePicture: ImageView

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference

    private val defaultProfilePictureUri = "@drawable/user_2"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_page)

        userNameTextView = findViewById(R.id.userNameTextView)
        userProfilePicture = findViewById(R.id.userProfilePicture)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        currentUser = firebaseAuth.currentUser!!
        databaseReference = database.reference.child("users").child(currentUser.uid)

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userName = snapshot.child("name").getValue(String::class.java)
                userNameTextView.text = userName?:"User"

                val placeHolderImage = R.drawable.user_2
                // Получить URL-адрес фотографии пользователя из базы данных
                val profilePictureUrl = snapshot.child("profilePictureUrl").getValue(String::class.java)

                // Загрузить и отобразить фотографию с помощью Glide
                if (!profilePictureUrl.isNullOrEmpty()) {
//

                    Glide.with(this@user_page)
                        .asBitmap()
                        .load(profilePictureUrl?: placeHolderImage)
                        .override(500,500)
                        .circleCrop()
                        .encodeFormat(Bitmap.CompressFormat.JPEG) // Установка формата компрессии
                        .encodeQuality(80) // Установка качества компрессии (0 - 100)
                        .into(userProfilePicture)

                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun onSelectProfilePicture(view: View) {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            selectedImageUri?.let {
                uploadImageToFirebaseStorage(selectedImageUri)
            }
        }
    }

    private fun uploadImageToFirebaseStorage(imageUri: Uri) {
        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child("profile_images/${currentUser.uid}")
        val uploadTask = imageRef.putFile(imageUri)

        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                // Сохранить URL-адрес в базе данных Firebase
                databaseReference.child("profilePictureUrl").setValue(uri.toString())
            }
        }.addOnFailureListener {

        }
    }

    override fun onResume() {
        super.onResume()
        if (userProfilePicture.drawable == null) {
            userProfilePicture.setImageURI(defaultProfilePictureUri.toUri())
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 1
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

    fun onClickButtonStatus(view: View) {
        startActivity(Intent(this, user_status::class.java))
    }

    fun onClickButtonDoc(view: View) {
        startActivity(Intent(this, user_doc::class.java))
    }

    fun onClickButtonNot(view: View) {
        startActivity(Intent(this, user_notify::class.java))
    }

    fun onClickButtonSet(view: View) {
        startActivity(Intent(this, user_page::class.java))
    }

    fun onClickButtonHelp(view: View) {
        startActivity(Intent(this, user_help::class.java))
    }
}