package com.example.todoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.database.TodosTypeConverters
import com.example.todoapp.database.TodoDao
import com.example.todoapp.models.Todo

@Database(entities = [ Todo::class ], version = 2)
@TypeConverters(TodosTypeConverters::class)
abstract class TasksDatabase: RoomDatabase(){

    abstract fun tasksDao(): TodoDao

}