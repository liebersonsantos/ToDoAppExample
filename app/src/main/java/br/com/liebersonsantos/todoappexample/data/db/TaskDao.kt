package br.com.liebersonsantos.todoappexample.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.liebersonsantos.todoappexample.data.model.Task

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Query("SELECT * FROM task_table ORDER BY name ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getTaskById(id: Int): LiveData<Task>

    @Query("SELECT * FROM task_table ORDER BY date ASC")
    fun getAllTasksOrderByDate(): LiveData<List<Task>>

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    @Query("DELETE FROM task_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: Task)
}