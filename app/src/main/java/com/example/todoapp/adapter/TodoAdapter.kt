package com.example.todoapp.adapter

import android.graphics.Paint
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.models.Todo
import com.example.todoapp.ui.listtasks.DATE_FORMAT
import com.md.todoapp.databinding.TaskListItemBinding

 class TodoAdapter(var todos: List<Todo>): RecyclerView.Adapter<TaskHolder>(){
    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = todos.get(position)
        holder.onBind(task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = TaskListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setData(list : List<Todo>){
        todos = list
        notifyDataSetChanged()
    }
}

class TaskHolder(val binding: TaskListItemBinding): RecyclerView.ViewHolder(binding.root) {
    private lateinit var todo: Todo
    init{
        binding.taskCheckbox.setOnCheckedChangeListener{_, isChecked ->
            toggleStrikeThrough(binding.taskTitle, isChecked)
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun onBind(todo: Todo){
        this.todo = todo
        binding.taskTitle.text = this.todo.title
        binding.taskDate.text = DateFormat.format(DATE_FORMAT, this.todo.dueDate)
    }

}