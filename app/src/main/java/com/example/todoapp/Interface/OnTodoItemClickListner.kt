package com.example.todoapp.Interface

import com.example.todoapp.RoomDatabase.Table.TodoDbTable

interface OnTodoItemClickListner {
    fun OnItemClick(position :Int, todo :TodoDbTable)
}