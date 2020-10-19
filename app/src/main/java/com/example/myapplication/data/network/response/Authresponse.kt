package com.example.myapplication.data.network.response

import com.example.myapplication.data.db.entites.User

data class Authresponse (
    val isSuccessful: Boolean?,
    val message: String?,
    val user:User?

)