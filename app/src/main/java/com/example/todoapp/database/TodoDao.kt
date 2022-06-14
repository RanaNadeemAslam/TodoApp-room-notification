package com.example.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.models.Todo
import java.util.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getTodos(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE id=(:id)")
    fun getTodoFromId(id: UUID): LiveData<Todo?>

    @Insert
    fun addTodo(todo: Todo)

}