package com.example.myapplication

import android.app.Notification
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.util.Calendar
import java.util.Locale

class prep_calendar : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prep_calendar)

        val SaveButton = findViewById<Button>(R.id.SaveButton)
        SaveButton.setOnClickListener {
            // Ваша логика сохранения данных

            // Показать сообщение о сохранении
            Toast.makeText(this, "Даты сохранены", Toast.LENGTH_SHORT).show()
        }
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

        calendarView.date = currentDate.timeInMillis

        // Устанавливаем слушатель выбора даты
        calendarView.setOnDateChangeListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            Toast.makeText(this, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
        }

        val buttons = mutableListOf<Button>()

        // Добавляем все кнопки в список и устанавливаем обработчики
        val buttonIds = arrayOf(R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14,R.id.button15, R.id.button16, R.id.button17, R.id.button18)
        for (buttonId in buttonIds) {
            val button = findViewById<Button>(buttonId)
            buttons.add(button)
            button.setOnClickListener { clickedButton ->
                handleButtonClick(clickedButton as Button, buttons)
            }
        }
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
    private fun handleButtonClick(clickedButton: Button, buttons: List<Button>) {
        clickedButton.setTextColor(Color.WHITE)
        clickedButton.setBackgroundResource(R.drawable.editted_button_4) // Можно выбрать любой цвет

        // Сбрасываем цвет для всех остальных кнопок
        for (button in buttons) {
            if (button != clickedButton) {
                button.setTextColor(Color.BLACK)
                button.setBackgroundResource(R.drawable.editted_button_2)
            }
        }
    }
}