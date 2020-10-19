package com.example.myapplication.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repositories.UserRepository
import com.example.myapplication.util.ApiException
import com.example.myapplication.util.Coroutines
import com.example.myapplication.util.NoInterNetException
import java.security.acl.NotOwnerException

class AuthviewModel(
    private  val repository: UserRepository
) : ViewModel() {
    var email: String ? = null
    var password: String? = null
    var name:String? = null
    var confirmedPassword: String? = null

    var authListener:AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginbuttonClick(view:View){
        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return

        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!,password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main

                }
                authListener?.onFailure(authResponse.message!!)

            }catch (e : ApiException){
                authListener?.onFailure(e.message!!)

            }catch (e: NoInterNetException){
                authListener?.onFailure(e.message!!)
            }

        }

    }

    fun onSignUpClick(view: View){
        authListener?.onStarted()
        if(password!=confirmedPassword){
            authListener?.onFailure("Password not matched")
            return
        }
        if (name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return

        }
        if (email.isNullOrEmpty()){
            authListener?.onFailure("email is required")
            return
        }
        Coroutines.main {
            try {
                val authResponse = repository.userSignUp(name!!,email!!,password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e:NotOwnerException){
                authListener?.onFailure(e.message!!)
            }
        }

    }
    fun onSignUp(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


}