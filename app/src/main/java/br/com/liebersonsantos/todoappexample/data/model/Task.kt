package br.com.liebersonsantos.todoappexample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.liebersonsantos.todoappexample.data.Status
import org.threeten.bp.OffsetDateTime

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val date: OffsetDateTime? = null,
    val state: String = Status.TODO.name
)