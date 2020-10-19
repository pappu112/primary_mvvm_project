package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.db.entites.Quotes
import com.example.myapplication.data.db.entites.User

@Database(
    entities = [User::class,Quotes::class],
    version = 1
)
abstract class Appdatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getQuotes():QuoteDao

    companion object{
        @Volatile
        private var instance : Appdatabase? = null

        private  val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                Appdatabase::class.java,
                "Mydatabase.db"
            ).build()


    }
}