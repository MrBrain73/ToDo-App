package com.example.todoapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTask() : LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Long) : LiveData<Task>

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTaskById(id : Long)

    @Insert
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}