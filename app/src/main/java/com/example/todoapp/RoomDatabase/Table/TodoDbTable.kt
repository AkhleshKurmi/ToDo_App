package com.example.todoapp.RoomDatabase.Table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey

@Entity
data class TodoDbTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("TodoId")
    var TodoId:Int,

    @ColumnInfo("TodoTitle")
    var TodoTitle:String,

    @ColumnInfo("TodoDiscription")
    var  TodoDiscription:String
)