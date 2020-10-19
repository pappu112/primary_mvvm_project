package com.example.myapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.db.entites.Quotes

@Dao
interface QuoteDao {
    @Insert
    fun saveAllQuotes(quotes: List<Quotes>)

    @Query("SELECT * FROM Quotes")
    fun getQuotes():LiveData<List<Quotes>>


}