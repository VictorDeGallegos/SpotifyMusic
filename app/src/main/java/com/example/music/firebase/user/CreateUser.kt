package com.example.music.firebase.user

data class CreateUser(
    var displayName: String = "",
    var email: String = "",
    var password: String = ""
)