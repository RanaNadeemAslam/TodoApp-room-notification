package com.example.todoapp.respository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.todoapp.database.TodoDao
import com.example.todoapp.database.TasksDatabase
import com.example.todoapp.models.Todo
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "todo-database"

class TodoRepository private constructor(private val context: Context){

    private val database: TasksDatabase = Room.databaseBuilder(
        context.applicationContext,
        TasksDatabase::class.java,
        DATABASE_NAME
    )
        .build()

    private val todoDao: TodoDao = database.tasksDao()
    private val executor = Executors.newSingleThreadExecutor()


    fun getTodos(): LiveData<List<Todo>> = todoDao.getTodos()

    fun getTodoFromId(id: UUID): LiveData<Todo?> = todoDao.getTodoFromId(id)

    fun addTodo(todo: Todo) {
        executor.execute {
            todoDao.addTodo(todo)
        }
    }

    companion object{
        private var INSTANCE: TodoRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = TodoRepository(context)
            }
        }

        fun get(): TodoRepository {
            if (INSTANCE != null)
            return INSTANCE ?:
            throw IllegalStateException("Repository was not initialized")
        }

    }
}