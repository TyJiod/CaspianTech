package com.example.myapplication

data class User(
    val name: String = "",
    val email: String = "",
    val profilePictureUrl: String = "",
    var userInterviewDate: String= "",
    var userInterviewTime: String=""
)