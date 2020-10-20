package com.example.myapplication.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repositories.QuotesRepository
import com.example.myapplication.data.repositories.UserRepository
import com.example.myapplication.ui.home.profile.ProfileViewModel
@Suppress("UNCHECKED_CAST")
class QuotesFactory (
    private val repository: QuotesRepository
    ) : ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return QuotesViewModel(repository) as T
        }
}
