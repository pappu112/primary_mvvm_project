package com.example.myapplication.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.db.Appdatabase
import com.example.myapplication.data.db.entites.Quotes
import com.example.myapplication.data.network.MyApi
import com.example.myapplication.data.network.SafeApirequest
import com.example.myapplication.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository (
   private val api:MyApi,
   private val db:Appdatabase
):SafeApirequest(){
    private val quotes = MutableLiveData<List<Quotes>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }

    }

    suspend fun getQuotes():LiveData<List<Quotes>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuotes().getQuotes()
        }
    }

    private suspend fun fetchQuotes(){
        if (isFetchNeeded()){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)

        }
    }
    private fun isFetchNeeded():Boolean{
        return true
    }

    private fun saveQuotes(quotes: List<Quotes>) {
        Coroutines.io {
            db.getQuotes().saveAllQuotes(quotes)
        }

    }

}