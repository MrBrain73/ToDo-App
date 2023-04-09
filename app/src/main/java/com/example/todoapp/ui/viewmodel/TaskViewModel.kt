package com.example.todoapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repo.TaskRepositories
import com.example.todoapp.model.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : ViewModel() {

    private val taskRepositories : TaskRepositories
    val tasks : LiveData<List<Task>>

    init {
        taskRepositories = TaskRepositories(application)
        tasks = taskRepositories.getAllTask()
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepositories.addTask(task)
        }
    }

    fun deleteTaskById(id : Long) {
        viewModelScope.launch {
            taskRepositories.deleteTaskById(id)
        }
    }

    fun getTaskById(id: Long) : LiveData<Task> {
        return taskRepositories.getTaskById(id)
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepositories.updateTask(task)
        }
    }
}