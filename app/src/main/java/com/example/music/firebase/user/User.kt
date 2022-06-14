package com.example.music.firebase.user

data class User(
    var users: UserInfo = UserInfo()
)

data class UserInfo(
    var authId: String = "",
    var email: String = "",
    var name: String = "",
    var birthday: String = "",
    var pass: String = ""
)


