package com.example.imaginato.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Email::class], version = 1, exportSchema = false)

abstract class AppDataBase : RoomDatabase() {

    abstract val databaseDao: DatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "Product Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}