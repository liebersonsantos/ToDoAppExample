package br.com.liebersonsantos.todoappexample.data.db.repository

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.todoappexample.data.model.Task

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
interface DbRepository {
    suspend fun insert(task: Task): Long
    fun getAllTasks(): LiveData<List<Task>>
    fun getTaskById(id: Int): LiveData<Task>
    fun getAllTasksOrderByDate(): LiveData<List<Task>>
    suspend fun deleteAll()
    suspend fun deleteTaskById(id: Int)
    suspend fun update(task: Task)
}
