package com.example.myapplication.ui.auth

import androidx.lifecycle.LiveData
import com.example.myapplication.data.db.entites.User

interface AuthListener {
    fun onStarted()
    fun  onSuccess(user: User)
    fun onFailure(message: String)
}