package com.example.todoapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapp.Interface.OnTodoItemClickListner
import com.example.todoapp.Interface.SetOnItemDeleteClickListner
import com.example.todoapp.Interface.SetOnItemEditClickListner
import com.example.todoapp.RoomDatabase.Table.TodoDbTable
import com.example.todoapp.databinding.CustomItemViewBinding

class TodoRecyclerViewAdapter(var list: List<TodoDbTable>, var deleteClickListner: SetOnItemDeleteClickListner,var setOnItemEditClickListner: SetOnItemEditClickListner,
    var itemclick : OnTodoItemClickListner) : Adapter<TodoRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: CustomItemViewBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = CustomItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var todoItem =list[position]
        var discriptionword = ""
        var stringlength = todoItem.TodoDiscription.length
        if (stringlength< 15){
            discriptionword = todoItem.TodoDiscription
        } else {
            discriptionword = todoItem.TodoDiscription.substring(0, minOf(15, stringlength))
        }
        holder.binding.todoTitle.text = todoItem.TodoTitle
        holder.binding.todoDescription.text = discriptionword
        holder.binding.deleteTodo.setOnClickListener {
            deleteClickListner.deleteTodo(position,todoItem)
        }
        holder.binding.editTodo.setOnClickListener {
            setOnItemEditClickListner.onTodoEdit(position,todoItem)
        }

        holder.itemView.setOnClickListener {
            itemclick.OnItemClick(position,todoItem)
        }
    }
}