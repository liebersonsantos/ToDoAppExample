package br.com.liebersonsantos.todoappexample.data.db.repository

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.todoappexample.data.db.TaskDao
import br.com.liebersonsantos.todoappexample.data.model.Task
import javax.inject.Inject

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class DbRepositoryImpl @Inject constructor(private val taskDao: TaskDao): DbRepository {
    override suspend fun insert(task: Task): Long = taskDao.insert(task)
    override fun getAllTasks(): LiveData<List<Task>> = taskDao.getAllTasks()
    override fun getTaskById(id: Long): LiveData<Task> = taskDao.getTaskById(id)
    override fun getAllTasksOrderByDate(): LiveData<List<Task>> = taskDao.getAllTasksOrderByDate()
    override suspend fun deleteAll() = taskDao.deleteAll()
    override suspend fun deleteTaskById(id: Long) = taskDao.deleteById(id)
    override suspend fun update(task: Task) = taskDao.update(task)

}