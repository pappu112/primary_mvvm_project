package com.example.myapplication.data.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quotes(
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    var quote:String,
    var author:String,
    var thumbnail:String,
    var created_at:String,
    var updated_at:String

)