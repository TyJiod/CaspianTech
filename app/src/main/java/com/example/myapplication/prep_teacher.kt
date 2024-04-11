package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView

class prep_teacher : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<TeacherData>()
    private lateinit var adapter: TeacherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prep_teacher)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)


        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        addDataToList()
        adapter = TeacherAdapter(mList)
        recyclerView.adapter = adapter
    }

//    private fun addDataToList(){
//        mList.add(TeacherData("Java"))
//    }

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

    fun onClickButton8(view: View) {
        startActivity(Intent(this, user_page::class.java))
    }

    fun onClickButtona1(view: View) {
        startActivity(Intent(this, teacher_a1::class.java))
    }

    fun onClickButtona2(view: View) {
        startActivity(Intent(this, teacher_a2::class.java))
    }

    fun onClickButtonb1(view: View) {
        startActivity(Intent(this, teacher_b1::class.java))
    }

    fun onClickButtonb2(view: View) {
        startActivity(Intent(this, teacher_b2::class.java))
    }

    fun onClickButtonc1(view: View) {
        startActivity(Intent(this, teacher_c1::class.java))
    }

    fun onClickButtonBagytova(view: View) {
        startActivity(Intent(this, bagytova::class.java))
    }

    fun onClickButtonLyan(view: View) {
        startActivity(Intent(this, lyan::class.java))
    }

    fun onClickButtonAyap(view: View) {
        startActivity(Intent(this, ayapbergenova::class.java))
    }
}