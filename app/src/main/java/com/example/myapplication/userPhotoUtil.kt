import android.graphics.Bitmap
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class UserPhotoUtil {
    private var databaseReference: DatabaseReference? = null

    fun setDatabaseReference(reference: DatabaseReference) {
        databaseReference = reference
    }

    fun loadUserPhoto(imageButton: ImageButton) {
        val placeHolderImage = R.drawable.user

        databaseReference?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val profilePictureUrl = snapshot.child("profilePictureUrl").getValue(String::class.java)
                profilePictureUrl?.let {
                    // Загрузить и установить изображение профиля с помощью Glide
                    Glide.with(imageButton.context)
                        .load(profilePictureUrl)
                        .circleCrop()
                        .override(75,75)
                        .encodeFormat(Bitmap.CompressFormat.JPEG) // Установка формата компрессии
                        .encodeQuality(80) // Установка качества компрессии (0 - 100)
                        .into(imageButton)
                } ?: run {
                    // Если URL-адрес не был получен, загружаем дефолтное изображение
                    Glide.with(imageButton.context)
                        .load(placeHolderImage)
                        .circleCrop()
                        .override(75,75)
                        .encodeFormat(Bitmap.CompressFormat.JPEG) // Установка формата компрессии
                        .encodeQuality(80) // Установка качества компрессии (0 - 100)
                        .into(imageButton)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибок
            }
        })
    }

}
