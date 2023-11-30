package com.example.todoapp.Factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.Repository.TodoRepository
import com.example.todoapp.TodoViewModelClass
import java.lang.IllegalArgumentException

class TodoViewModelFactory(var context: Context, var todoRepository : TodoRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModelClass::class.java)){
        return TodoViewModelClass(context,todoRepository) as T
    }
        throw IllegalArgumentException("Illegal class")
    }
}