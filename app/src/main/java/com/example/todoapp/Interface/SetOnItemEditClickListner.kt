package com.example.todoapp.Interface

import com.example.todoapp.RoomDatabase.Table.TodoDbTable

interface SetOnItemEditClickListner {
    fun onTodoEdit(position:Int ,List :TodoDbTable)
}