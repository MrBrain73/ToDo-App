package com.example.todoapp.data.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todoapp.data.local.AppDataBase
import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.model.Task

class TaskRepositories(application: Application) {

    private val taskDao : TaskDao

    init {
        val db = AppDataBase.getDataBase(application.applicationContext)
        taskDao = db.getTaskDao()
    }

    fun getAllTask() : LiveData<List<Task>> {
        return taskDao.getAllTask()
    }

    fun getTaskById(id: Long) : LiveData<Task> {
        return taskDao.getTaskById(id)
    }

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun deleteTaskById(id : Long) {
        taskDao.deleteTaskById(id)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }
}