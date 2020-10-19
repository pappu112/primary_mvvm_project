package com.example.myapplication.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.db.Appdatabase
import com.example.myapplication.data.db.entites.User
import com.example.myapplication.data.network.MyApi
import com.example.myapplication.data.network.SafeApirequest
import com.example.myapplication.data.network.response.Authresponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository (
    private val api:MyApi,
    private val db: Appdatabase
): SafeApirequest() {
    suspend fun userLogin(email:String,password: String): Authresponse{
       return apiRequest {api.userLogin(email, password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

    suspend fun userSignUp(name:String,email:String,password: String):Authresponse{
        return apiRequest { api.userSignup(name,email,password) }
    }

}