package com.example.lab3android.Model

import android.net.Uri

data class Contact(
    val name: String,
    val email: String,
    val phone: String,
    val photoUri: Uri
)
