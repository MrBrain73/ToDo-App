package com.example.todoapp.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTasklistBinding
import com.example.todoapp.model.Task
import com.example.todoapp.ui.adapters.TaskListAdapter
import com.example.todoapp.ui.viewmodel.TaskViewModel
import com.example.todoapp.ui.viewmodel.TaskViewModelFactory

class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTasklistBinding
    private lateinit var adapter: TaskListAdapter
    private lateinit var viewModel : TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasklistBinding.inflate(layoutInflater, container, false)

        viewModel = (activity as MainActivity).viewModel
        viewModel.tasks.observe(viewLifecycleOwner) { adapter.data = it }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.taskList.layoutManager = LinearLayoutManager(context)
        binding.taskList.adapter = adapter

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_taskAddFragment)
        }
    }
}