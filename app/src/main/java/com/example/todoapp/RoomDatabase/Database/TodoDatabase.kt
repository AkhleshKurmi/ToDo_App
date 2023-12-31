package com.example.todoapp.RoomDatabase.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.RoomDatabase.Dao.TodoDao
import com.example.todoapp.RoomDatabase.Table.TodoDbTable

@Database(entities = [TodoDbTable::class], version = 1)
abstract class TodoDatabase :RoomDatabase() {
    abstract val  todoDao :TodoDao
    companion object{
        @Volatile
        private var INSTANCE : TodoDatabase? = null

        fun getInstance(context: Context):TodoDatabase{
            var instance = INSTANCE
            if (instance == null){
                instance = Room.databaseBuilder(context,TodoDatabase::class.java,"TodoDb").build()
            }
            return instance
        }


    }

}