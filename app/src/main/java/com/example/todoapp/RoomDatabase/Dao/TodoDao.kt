package com.example.todoapp.RoomDatabase.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.RoomDatabase.Table.TodoDbTable

@Dao
interface TodoDao {

    @Insert()
    suspend fun insetTodo(todotable:TodoDbTable) :Long

    @Update
    suspend fun updateTodo(todotable: TodoDbTable):Int

    @Query("SELECT * FROM  TodoDbTable")
    fun fetchTodo() :LiveData<List<TodoDbTable>>

    @Delete
    suspend fun deleteARow(todotable: TodoDbTable):Int

//    @Query("DELETE FROM TodoDbTable")
//    suspend fun deleteAll():Int
}