package com.example.myapplication.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.example.myapplication.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}
