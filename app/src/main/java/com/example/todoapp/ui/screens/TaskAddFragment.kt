package com.example.todoapp.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTaskaddBinding
import com.example.todoapp.model.Task
import com.example.todoapp.ui.viewmodel.TaskViewModel

class TaskAddFragment : Fragment() {

    private lateinit var binding: FragmentTaskaddBinding
    private lateinit var viewModel : TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskaddBinding.inflate(layoutInflater, container, false)

        viewModel = (activity as MainActivity).viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskAddFragment_to_taskListFragment)
        }

        binding.addButton.setOnClickListener {
            val tempTask = makeTaskFromEditText()
            if (tempTask != null) {
                viewModel.addTask(tempTask)
                findNavController().navigate(R.id.action_taskAddFragment_to_taskListFragment)
            }
        }
    }

    private fun makeTaskFromEditText() : Task? {
        var tempTask : Task? = null
        if (binding.editTextTitle.editText?.text.toString() == "")
            binding.editTextTitle.error = "Empty field"
        else
            binding.editTextTitle.error = null
        if (binding.editTextDescription.editText?.text.toString() == "")
            binding.editTextDescription.error = "Empty field"
        else
            binding.editTextDescription.error = null
        if (binding.editTextTitle.editText?.text.toString() != "" &&
            binding.editTextDescription.editText?.text.toString() != "") tempTask = Task(
            0,
            binding.editTextTitle.editText?.text.toString(),
            binding.editTextDescription.editText?.text.toString(),
            false
        )
        return tempTask
    }
}