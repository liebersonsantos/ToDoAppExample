package br.com.liebersonsantos.todoappexample.domain.di

import android.content.Context
import androidx.room.Room
import br.com.liebersonsantos.todoappexample.data.db.TaskDao
import br.com.liebersonsantos.todoappexample.data.db.TaskDatabase
import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepository
import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun providerTaskDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            "task_db"
        ).build()

    @Singleton
    @Provides
    fun providerTaskDao(db: TaskDatabase) = db.taskDao()

    @Singleton
    @Provides
    fun providerDbRepository(taskDao: TaskDao): DbRepository =
        DbRepositoryImpl(taskDao)

}