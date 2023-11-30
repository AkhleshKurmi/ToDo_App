package com.example.todoapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipDescription
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todoapp.Activity.TodoViewActivity
import com.example.todoapp.Adapter.TodoRecyclerViewAdapter
import com.example.todoapp.Factory.TodoViewModelFactory
import com.example.todoapp.Interface.OnTodoItemClickListner
import com.example.todoapp.Interface.SetOnItemDeleteClickListner
import com.example.todoapp.Interface.SetOnItemEditClickListner
import com.example.todoapp.Repository.TodoRepository
import com.example.todoapp.RoomDatabase.Database.TodoDatabase
import com.example.todoapp.RoomDatabase.Table.TodoDbTable
import com.example.todoapp.databinding.ActivityTodoBinding
import com.example.todoapp.databinding.DialogViewBinding

class TodoActivity : AppCompatActivity(){
    lateinit var binding : ActivityTodoBinding
    lateinit var todoViewModel : TodoViewModelClass
    lateinit var todoRecyclerViewAdapter: TodoRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo)
        var todoDao = TodoDatabase.getInstance(this).todoDao
        var repository : TodoRepository = TodoRepository(todoDao)

       todoViewModel = ViewModelProvider(this,
           TodoViewModelFactory(this,repository))[TodoViewModelClass::class.java]

        binding.lifecycleOwner = this
        binding.myViewModel = todoViewModel
//        binding.mainActivity = this
//

//        bindingdialog = DataBindingUtil.setContentView(this,R.layout.custom_dialog)
        var bindingdialog : DialogViewBinding = DialogViewBinding.inflate(layoutInflater)
        var createDialog = Dialog(this)
        createDialog.setContentView(bindingdialog.root)
        var layoutManager = WindowManager.LayoutParams()
        layoutManager.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutManager.height = WindowManager.LayoutParams.WRAP_CONTENT
        createDialog.window!!.attributes = layoutManager
//        title.value = "Save"
        bindingdialog.myViewModel = todoViewModel

        bindingdialog.lifecycleOwner = this
        createDialog.setCancelable(false)




        todoViewModel.createdialog.observe(this, Observer {
            if (it== true){
                createDialog.show()
            }else{
                createDialog.dismiss()
            }
        })

//        todoViewModel.cancleBtn.observe(this, Observer {
//            if (it == true){
//
//            }
//        })

        todoViewModel.statusMessage.observe(this, Observer {
            it.getContentIfNotHandled()!!.apply {
                Toast.makeText(this@TodoActivity, "$this", Toast.LENGTH_SHORT).show()
            }
        })

     binding.todoRv.layoutManager = GridLayoutManager(this,2)
        todoViewModel.todoLiveData.observe(this, Observer {
            todoRecyclerViewAdapter =TodoRecyclerViewAdapter(it,object : SetOnItemDeleteClickListner{
                override fun deleteTodo(position: Int, List: TodoDbTable) {
                 alertMessage(List)
                }

            }, object : SetOnItemEditClickListner{
                override fun onTodoEdit(position: Int, List: TodoDbTable) {
                 todoViewModel.updateTodo(List)
                }

            }, object : OnTodoItemClickListner{
                override fun OnItemClick(position: Int, todo: TodoDbTable) {

                    intentActivity(todo.TodoTitle,todo.TodoDiscription)

                }

            })
            binding.todoRv.adapter = todoRecyclerViewAdapter
            todoRecyclerViewAdapter.notifyDataSetChanged()
        })

    }

    fun alertMessage(todoDbTable: TodoDbTable){
        var appname = applicationInfo.loadLabel(packageManager).toString()
         var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("$appname")
        alertDialog.setMessage("Are you sure to delete")
        alertDialog.setPositiveButton("Yes",object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                todoViewModel.DeleteATodo(todoDbTable)
                dialog!!.dismiss()
            }
        })
        alertDialog.setNegativeButton("No", object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
               dialog!!.dismiss()
            }

        })

        var dialogalert = alertDialog.create()
        dialogalert.show()
    }


    private fun intentActivity(title:String,description: String){
        val intent = Intent(this, TodoViewActivity::class.java)
        intent.putExtra("title",title)
        intent.putExtra("description",description)
        startActivity(intent)
    }



}