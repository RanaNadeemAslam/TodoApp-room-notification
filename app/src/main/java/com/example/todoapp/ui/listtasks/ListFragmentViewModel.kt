package com.example.todoapp.ui.listtasks

import androidx.lifecycle.ViewModel
import com.example.todoapp.respository.TodoRepository

class ListFragmentViewModel: ViewModel() {
    private val todoRepository = TodoRepository.get()

    val taskList = todoRepository.getTodos()

}