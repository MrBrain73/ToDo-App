package com.example.todoapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTaskviewBinding
import com.example.todoapp.model.Task
import com.example.todoapp.ui.screens.TaskListFragmentDirections

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    var data : List<Task> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set (newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: ItemTaskviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTaskviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = data[position]

        with(holder.binding) {
            taskTitle.text = task.title
            taskText.text = task.description

            root.setOnClickListener {
                val action = TaskListFragmentDirections
                    .actionTaskListFragmentToTaskUpdateFragment().setTaskId(task.id)

                it.findNavController().navigate(action)
            }
        }
    }
}