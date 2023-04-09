package com.example.todoapp.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTaskupdateBinding
import com.example.todoapp.model.Task
import com.example.todoapp.ui.viewmodel.TaskViewModel

class TaskUpdateFragment : Fragment() {

    private lateinit var binding: FragmentTaskupdateBinding
    private lateinit var viewModel : TaskViewModel
    private val args by navArgs<TaskUpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskupdateBinding.inflate(layoutInflater, container, false)

        viewModel = (activity as MainActivity).viewModel
        viewModel.getTaskById(args.taskId).observe(viewLifecycleOwner) {
            println(it.description)
            with(binding) {
                editTextTitle.editText?.setText(it.title)
                editTextDescription.editText?.setText(it.description)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskUpdateFragment_to_taskListFragment)
        }

        binding.saveButton.setOnClickListener {
            val tempTask = makeTaskFromEditText()
            if (tempTask != null) {
                viewModel.updateTask(tempTask)
                findNavController().navigate(R.id.action_taskUpdateFragment_to_taskListFragment)
            }
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteTaskById(args.taskId)
            findNavController().navigate(R.id.action_taskUpdateFragment_to_taskListFragment)
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
            args.taskId,
            binding.editTextTitle.editText?.text.toString(),
            binding.editTextDescription.editText?.text.toString(),
            false
        )
        return tempTask
    }
}