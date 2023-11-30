package com.example.todoapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.Factory.TodoViewModelFactory
import com.example.todoapp.R
import com.example.todoapp.Repository.TodoRepository
import com.example.todoapp.RoomDatabase.Database.TodoDatabase
import com.example.todoapp.RoomDatabase.Table.TodoDbTable
import com.example.todoapp.TodoViewModelClass
import com.example.todoapp.databinding.ActivityTodoViewBinding

class TodoViewActivity : AppCompatActivity() {
    lateinit var binding:ActivityTodoViewBinding
//    lateinit var myViewModel : TodoViewModelClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo_view)
    var data =intent
     var title = data.getStringExtra("title")
    var description = data.getStringExtra("description")

    binding.titleText.text = title
    binding.descriptionText.text = description
//        var todoDao = TodoDatabase.getInstance(this).todoDao
//        var repository : TodoRepository = TodoRepository(todoDao)
//
//        myViewModel = ViewModelProvider(this,
//            TodoViewModelFactory(this,repository)
//        )[TodoViewModelClass::class.java]
//        binding.viewmodel =  myViewModel
//        binding.lifecycleOwner = this

    }
}