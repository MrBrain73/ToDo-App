package com.example.todoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.model.Task

@Database(
    version = 1,
    entities = [ Task::class ],
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getTaskDao() : TaskDao

    companion object {
        private var INSTANCE : AppDataBase? = null

        fun getDataBase(context: Context) : AppDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "app_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}