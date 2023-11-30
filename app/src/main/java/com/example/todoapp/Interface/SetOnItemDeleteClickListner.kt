package com.example.todoapp.Interface

import com.example.todoapp.RoomDatabase.Table.TodoDbTable

interface SetOnItemDeleteClickListner {
    fun deleteTodo(position:Int ,List :TodoDbTable)
}