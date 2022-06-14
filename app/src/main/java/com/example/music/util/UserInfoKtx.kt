package com.example.music.util

import com.example.music.firebase.user.UserInfo

fun UserInfo.isProfileComplete(): Boolean =
    name.isNotEmpty()

