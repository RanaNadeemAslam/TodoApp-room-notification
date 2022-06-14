package com.example.todoapp

import android.app.Application
import com.example.todoapp.respository.TodoRepository

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TodoRepository.initialize(this)
    }
}