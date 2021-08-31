package br.com.liebersonsantos.todoappexample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.liebersonsantos.todoappexample.data.Status

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val status: String = Status.TODO.name
)