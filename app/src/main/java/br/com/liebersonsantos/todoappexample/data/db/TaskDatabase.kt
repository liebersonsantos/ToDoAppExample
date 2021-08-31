package br.com.liebersonsantos.todoappexample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.liebersonsantos.todoappexample.data.model.Task

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TaskDatabase: RoomDatabase(){
    abstract fun taskDao(): TaskDao
}