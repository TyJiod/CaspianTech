package com.example.myapplication

import UserPhotoUtil
import android.app.Notification
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class prep_calendar : ComponentActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    private lateinit var saveButton: Button
    private var selectedTime: String = ""
    private var selectedDate: String = ""

    private lateinit var selectTime8AmButton : Button
    private lateinit var selectTime9AmButton : Button
    private lateinit var selectTime10AmButton : Button
    private lateinit var selectTime11AmButton : Button
    private lateinit var selectTime12AmButton : Button
    private lateinit var selectTime13AmButton : Button
    private lateinit var selectTime14AmButton : Button
    private lateinit var selectTime15AmButton : Button
    private lateinit var selectTime16AmButton : Button
    private lateinit var selectTime17AmButton : Button
    private lateinit var selectTime18AmButton : Button
    private lateinit var profileImageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prep_calendar)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        currentUser = firebaseAuth.currentUser!!
        databaseReference = database.reference.child("users").child(currentUser.uid)

        profileImageButton = findViewById(R.id.profileImageButton)
        val userPhotoUtil = UserPhotoUtil()

        userPhotoUtil.setDatabaseReference(databaseReference)

        userPhotoUtil.loadUserPhoto(profileImageButton)

        val locale = Locale("en")
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)
        createConfigurationContext(config)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)

        // Устанавливаем текущую дату
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        selectTime8AmButton = findViewById(R.id.selectTime8Am)
        selectTime9AmButton = findViewById(R.id.selectTime9Am)
        selectTime10AmButton = findViewById(R.id.selectTime10Am)
        selectTime11AmButton = findViewById(R.id.selectTime11Am)
        selectTime12AmButton = findViewById(R.id.selectTime12Am)
        selectTime13AmButton = findViewById(R.id.selectTime13Am)
        selectTime14AmButton = findViewById(R.id.selectTime14Am)
        selectTime15AmButton = findViewById(R.id.selectTime15Am)
        selectTime16AmButton = findViewById(R.id.selectTime16Am)
        selectTime17AmButton = findViewById(R.id.selectTime17Am)
        selectTime18AmButton = findViewById(R.id.selectTime18Am)
        saveButton = findViewById(R.id.SaveButton)  // Инициализация кнопки сохранения

        calendarView.date = currentDate.timeInMillis

        calendarView.setOnDateChangeListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"  // Обновляем выбранную дату
            Toast.makeText(this, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
        }

        val buttons = mutableListOf<Button>()

        // Добавляем все кнопки в список и устанавливаем обработчики
        val buttonIds = arrayOf(
            R.id.selectTime8Am, R.id.selectTime9Am, R.id.selectTime10Am,
            R.id.selectTime11Am, R.id.selectTime12Am, R.id.selectTime13Am,
            R.id.selectTime14Am, R.id.selectTime15Am, R.id.selectTime16Am,
            R.id.selectTime17Am, R.id.selectTime18Am
        )
        for (buttonId in buttonIds) {
            val button = findViewById<Button>(buttonId)
            buttons.add(button)
            button.setOnClickListener { clickedButton ->
                handleButtonSelection(clickedButton as Button, buttons)
            }
        }

        var selectedTime = "00:00 Am"

        selectTime8AmButton.setOnClickListener {
            selectedTime = "8:00 AM"
            handleButtonSelection(selectTime8AmButton, buttons)
        }


        selectTime9AmButton.setOnClickListener {
            selectedTime = "9:00 AM"
            handleButtonSelection(selectTime9AmButton, buttons)
        }

        selectTime10AmButton.setOnClickListener {
            selectedTime = "10:00 AM"
            handleButtonSelection(selectTime10AmButton, buttons)
        }
        selectTime11AmButton.setOnClickListener {
            selectedTime = "11:00 AM"
            handleButtonSelection(selectTime11AmButton, buttons)
        }
        selectTime12AmButton.setOnClickListener {
            selectedTime = "12:00 PM"
            handleButtonSelection(selectTime12AmButton, buttons)
        }
        selectTime13AmButton.setOnClickListener {
            selectedTime = "13:00 PM"
            handleButtonSelection(selectTime13AmButton, buttons)
        }
        selectTime14AmButton.setOnClickListener {
            selectedTime = "14:00 PM"
            handleButtonSelection(selectTime14AmButton, buttons)
        }
        selectTime15AmButton.setOnClickListener {
            selectedTime = "15:00 PM"
            handleButtonSelection(selectTime15AmButton, buttons)
        }
        selectTime16AmButton.setOnClickListener {
            selectedTime = "16:00 PM"
            handleButtonSelection(selectTime16AmButton, buttons)
        }
        selectTime17AmButton.setOnClickListener {
            selectedTime = "17:00 PM"
            handleButtonSelection(selectTime17AmButton, buttons)
        }
        selectTime18AmButton.setOnClickListener {
            selectedTime = "18:00 PM"
            handleButtonSelection(selectTime18AmButton, buttons)
        }



        saveButton.setOnClickListener {
            // Проверяем, что выбрана какая-то дата и время
            if (selectedDate.isNotEmpty() && selectedTime.isNotEmpty()) {
                // Выполняем сохранение с выбранной датой и временем
                saveInterviewDateTime(selectedDate, selectedTime)
                Toast.makeText(this, "Даты сохранены", Toast.LENGTH_SHORT).show()
            } else {
                // Выводим сообщение, если дата или время не выбраны
                Toast.makeText(this, "Выберите дату и время", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveInterviewDateTime(selectedDate: String, selectedTime: String) {
        val userData = mapOf(
            "userInterviewDate" to selectedDate,
            "userInterviewTime" to selectedTime
        )
        val userReference = databaseReference.child("userInterviewData")

        userReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    userReference.updateChildren(userData)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this@prep_calendar,
                                "Данные обновлены",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(
                                this@prep_calendar,
                                "Ошибка обновления данных: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    // Данные отсутствуют, создаем новую запись
                    userReference.setValue(userData)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this@prep_calendar,
                                "Данные сохранены",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(
                                this@prep_calendar,
                                "Ошибка сохранения данных: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Обработка ошибок при чтении данных
                Toast.makeText(
                    this@prep_calendar,
                    "Ошибка чтения данных: ${databaseError.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    fun Notification(view: View) {
        startActivity(Intent(this, user_notify::class.java))
    }

    fun ProfileIcon(view: View) {
        startActivity(Intent(this, user_page::class.java))
    }

    fun HomePage(view: View) {
        startActivity(Intent(this, HomePage::class.java))
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
    private fun handleButtonSelection(selectedButton: Button, allButtons: List<Button>) {
        // Устанавливаем цвет для выбранной кнопки
        selectedButton.setTextColor(Color.WHITE)
        selectedButton.setBackgroundResource(R.drawable.editted_button_4)

        // Сбрасываем стили для всех остальных кнопок
        for (button in allButtons) {
            if (button != selectedButton) {
                button.setTextColor(Color.BLACK)
                button.setBackgroundResource(R.drawable.editted_button_2)
            }
        }
    }
}