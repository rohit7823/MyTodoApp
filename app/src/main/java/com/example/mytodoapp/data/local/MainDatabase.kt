package com.example.mytodoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {

    companion object {
        var mainDbInstance: MainDatabase? = null

        fun getMainDbInstance(context: Context): MainDatabase? {
            if (mainDbInstance == null) {
                mainDbInstance = Room.databaseBuilder(context.applicationContext, MainDatabase::class.java, "TodoDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return mainDbInstance
        }
    }


}
