package com.example.todoapp.Repository

import androidx.lifecycle.LiveData
import com.example.todoapp.RoomDatabase.Dao.TodoDao
import com.example.todoapp.RoomDatabase.Table.TodoDbTable

class TodoRepository(var todoDao: TodoDao) {

    suspend fun InsertTodo(todoDbTable: TodoDbTable):Long{
       return todoDao.insetTodo(todoDbTable)
    }
    var todoData :LiveData<List<TodoDbTable>> = todoDao.fetchTodo()

    suspend fun updateTOdo(todoDbTable: TodoDbTable) :Int{

        return todoDao.updateTodo(todoDbTable)
    }

    suspend fun deleteATodo(todoDbTable: TodoDbTable):Int{
        return todoDao.deleteARow(todoDbTable)
    }
}