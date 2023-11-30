package com.example.todoapp


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.Repository.TodoRepository
import com.example.todoapp.RoomDatabase.Table.TodoDbTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModelClass(var context: Context, var todoRepository: TodoRepository) : ViewModel() {
    var title: MutableLiveData<String> = MutableLiveData()
    var btnSaveUpdateText: MutableLiveData<String> = MutableLiveData()
//    var cancleBtn: MutableLiveData<Boolean> = MutableLiveData()
    var createdialog: MutableLiveData<Boolean> = MutableLiveData()
    var btnSaveUpdate: MutableLiveData<Boolean> = MutableLiveData()
    var todoTitle: MutableLiveData<String> = MutableLiveData()
    var todoDescription: MutableLiveData<String> = MutableLiveData()
    var statusMessage: MutableLiveData<Event<String>> = MutableLiveData<Event<String>>()
    var todoLiveData : LiveData<List<TodoDbTable>> = todoRepository.todoData
    var rowid :MutableLiveData<Int> = MutableLiveData()
    init {
        title.value = "Create"
        btnSaveUpdateText.value = "Save"
        todoTitle.value = "gkgkj"
        todoDescription.value = ""
//        cancleBtn.value = false
        createdialog.value = false
        btnSaveUpdate.value = false
        rowid.value = 0
    }

    fun insertOrUpdateTodo() {
        if (todoDescription.value.isNullOrEmpty() || todoTitle.value.isNullOrEmpty()) {
            statusMessage.value = Event("Fields  Are Mandatory")
        } else {
            if (btnSaveUpdate.value!!) {
              viewModelScope.launch(Dispatchers.IO){

                  var updatedrow = todoRepository.updateTOdo(TodoDbTable(rowid.value!!,todoTitle.value!!,todoDescription.value!!))

                   withContext(Dispatchers.Main){
                       if(updatedrow >0 ) {
                           statusMessage.value = Event("Updated Successfully")
                           todoTitle.value = ""
                           todoDescription.value = ""
                           btnSaveUpdate.value = false
                           createdialog.value = false
                       }else{
                           statusMessage.value = Event("Something went wrong")
                       }
                   }

              }
            } else {
                viewModelScope.launch(Dispatchers.IO) {
                    var rowId = todoRepository.InsertTodo(TodoDbTable(0,todoTitle.value!!,todoDescription.value!!))
                    withContext(Dispatchers.Main) {
                        if (rowId > 0) {
                            statusMessage.value = Event("Saved SuccessFully")
                            todoTitle.value = ""
                            todoDescription.value = ""

                            createdialog.value = false

                        } else {
                            statusMessage.value = Event("Something went wrong")
                        }
                    }

                }
            }
        }
    }

    fun DeleteATodo(todoDbTable: TodoDbTable){
        viewModelScope.launch(Dispatchers.IO) {
            var deletedRow = todoRepository.deleteATodo(todoDbTable)
            withContext(Dispatchers.Main){
                if (deletedRow > 0){
                    statusMessage.value = Event("Deleted Successfully")
                } else {
                    statusMessage.value = Event("Something went Wrong")
                }
            }
        }
    }

    fun createDialog() {
        createdialog.value =true
        title.value = "Create"
        btnSaveUpdateText.value = "Save"
        todoTitle.value = ""
        todoDescription.value = ""

    }

    fun cancelDialog() {
        createdialog.value = false
    }
  fun updateTodo(todoDbTable: TodoDbTable){
    todoTitle.value = todoDbTable.TodoTitle
      todoDescription.value = todoDbTable.TodoDiscription
      createdialog.value = true
      rowid.value = todoDbTable.TodoId
      title.value = "Update"
      btnSaveUpdateText.value = "Update"
      btnSaveUpdate.value = true
   }

//    fun onItemClick(todoDbTable: TodoDbTable){
//        todoTitle.value = todoDbTable.TodoTitle
//        todoDescription.value = todoDbTable.TodoDiscription
//    }


}