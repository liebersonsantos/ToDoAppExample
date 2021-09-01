package br.com.liebersonsantos.todoappexample.data.db.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import br.com.liebersonsantos.todoappexample.data.db.TaskDao
import br.com.liebersonsantos.todoappexample.data.db.TaskDatabase
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.waitForValue
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by lieberson on 8/31/21.
 *
 * @author lieberson.xsantos@gmail.com
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DbRepositoryImplTest: TestCase() {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var taskDao: TaskDao
    private lateinit var db: TaskDatabase

    @Before
    fun createDb(){
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        taskDao = db.taskDao()
    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun insertAndGetAllTasks() = runBlocking{
        //Given
        val task = Task(name = "test")
        //when
        taskDao.insert(task)
        //then
        val allTasks = taskDao.getAllTasks().waitForValue()
        assertEquals(allTasks[0].name, task.name)
    }

    @Test
    fun getAllTasks(){
        //given

        //when
        val allTasks = taskDao.getAllTasks().waitForValue()
        //then
        assertEquals(allTasks.size, 0)
    }

    @Test
    fun getTaskById() = runBlocking {
        //given
        val task = Task(name = "test")
        taskDao.insert(task)
        val allTasks = taskDao.getAllTasks().waitForValue()
        val selectedTask = allTasks[0]
        //when
        val taskById = taskDao.getTaskById(selectedTask.id).waitForValue()
        //then
        assertEquals(taskById.id, selectedTask.id)
    }

    @Test
    fun deleteAllTasks() = runBlocking{
        //given
        val task = Task(name = "test")
        taskDao.insert(task)
        //when
        taskDao.deleteAll()
        //then
        val allTasks = taskDao.getAllTasks().waitForValue()
        assertThat(allTasks).doesNotContain(task)
    }

    @Test
    fun deleteTaskById() = runBlocking {
        //given
        val task = Task(name = "test")
        taskDao.insert(task)
        val allTasks = taskDao.getAllTasks().waitForValue()
        val selectedTask = allTasks[0]
        //when
        taskDao.deleteById(selectedTask.id)
        //then
        val checkTasks = taskDao.getAllTasks().waitForValue()
        assertEquals(checkTasks.size, allTasks.size -1)
    }

    @Test
    fun updateTask() = runBlocking{
        //given
        val task = Task(name = "test")
        taskDao.insert(task)
        val allTasks = taskDao.getAllTasks().waitForValue()
        val updatedTask = allTasks[0].copy(name = "new test")
        //when
        taskDao.update(updatedTask)
        //then
        val updated = taskDao.getAllTasks().waitForValue()
        assertEquals(updated[0].name, updatedTask.name)
    }
}