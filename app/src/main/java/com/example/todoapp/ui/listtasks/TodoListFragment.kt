package com.example.todoapp.ui.listtasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.models.Todo
import com.example.todoapp.ui.addtasks.AddFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.md.todoapp.R
import com.md.todoapp.databinding.FragmentTodoListBinding
import java.util.*

const val DATE_FORMAT = "EEEE, MMM, dd, yyyy"

class TodoListFragment : Fragment() {

    interface Callbacks {
        fun onTaskSelect(view: View?, taskId: UUID)
    }
    private lateinit var binding: FragmentTodoListBinding

    val taskListViewModel by viewModels<ListFragmentViewModel>()

//    private var callbacks: Callbacks? = null
    private lateinit var fabButton: FloatingActionButton
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var subTitle: TextView
    private var adapter: TodoAdapter = TodoAdapter(emptyList())

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        callbacks = context as Callbacks?
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTodoListBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            val fragment = AddFragment()
            parentFragmentManager
                .beginTransaction()
                .addSharedElement(it, "list_to_add")
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding.tasksList.adapter = adapter

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (requireActivity() as AppCompatActivity)

        taskListViewModel.taskList.observe(viewLifecycleOwner
        ) { tasks ->
            tasks?.let {
                updateList(tasks)
            }
        }

    }

    override fun onDetach() {
        super.onDetach()
//        callbacks = null
    }

    private fun updateList(todos: List<Todo>) {
        adapter.setData(todos)
    }

}
