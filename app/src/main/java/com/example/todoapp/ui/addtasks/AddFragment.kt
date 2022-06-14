package com.example.todoapp.ui.addtasks

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todoapp.ui.listtasks.DATE_FORMAT
import com.google.android.material.snackbar.Snackbar
import com.md.todoapp.databinding.FragmentAddTodoBinding
import com.peterdpong.checked.DatePickerFragment
import java.util.*

private const val DIALOG_DATE = "DateDialog"
private const val RETURN_DATE = 0
private const val NO_TITLE_TEXT = "Add a task title to save."

class AddFragment : Fragment(), DatePickerFragment.Callbacks {

    private lateinit var binding: FragmentAddTodoBinding
    val addFragmentViewModel by viewModels<AddFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onStart() {
        super.onStart()
        updateUI()

        val titleWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                addFragmentViewModel.currentTodo.title = s.toString()
            }
        }

        val descWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //Intentionally Left Blank
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Intentionally Left Blank
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                addFragmentViewModel.currentTodo.desc = s.toString()
            }
        }

        binding.titleTextInput.addTextChangedListener(titleWatcher)
        binding.descTextInput.addTextChangedListener(descWatcher)

        binding.taskDate.setOnClickListener {
            DatePickerFragment.newInstance(addFragmentViewModel.currentTodo.dueDate).apply {
                setTargetFragment(this@AddFragment, RETURN_DATE)
                show(this@AddFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }

        binding.savebtn.setOnClickListener {
            if(addFragmentViewModel.currentTodo.title.isEmpty()){
                Snackbar.make(binding.addtasklayout, NO_TITLE_TEXT, Snackbar.LENGTH_SHORT)
                .show()
            }else{
                addFragmentViewModel.addTask(addFragmentViewModel.currentTodo)
                parentFragmentManager.popBackStack()
            }
        }

        binding.cancelbtn.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

    }

    override fun onDateSelected(date: Date) {
        addFragmentViewModel.currentTodo.dueDate = date
        updateUI()
    }

    private fun updateUI() {
        binding.titleTextInput.setText(addFragmentViewModel.currentTodo.title)
        binding.descTextInput.setText(addFragmentViewModel.currentTodo.desc)
        binding.taskDate.setText(DateFormat.format(DATE_FORMAT, addFragmentViewModel.currentTodo.dueDate))
    }
}
