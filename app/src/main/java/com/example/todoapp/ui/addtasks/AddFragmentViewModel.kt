package com.example.todoapp.ui.addtasks

import androidx.lifecycle.ViewModel
import com.example.todoapp.models.Todo
import com.example.todoapp.respository.TodoRepository

class AddFragmentViewModel: ViewModel() {
    var currentTodo: Todo = Todo()

    private val todoRepository = TodoRepository.get()

    fun addTask(todo: Todo){
        todoRepository.addTodo(todo)
    }

}