package com.example.myapplication.data.network.response

import com.example.myapplication.data.db.entites.Quotes

data  class QuotesResponse (
    val isSuccessful: Boolean,

    val quotes:List<Quotes>
)
