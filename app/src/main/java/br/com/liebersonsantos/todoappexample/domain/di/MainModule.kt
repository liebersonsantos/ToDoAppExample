package br.com.liebersonsantos.todoappexample.domain.di

import android.content.Context
import br.com.liebersonsantos.todoappexample.customapplication.CustomApplication
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.deleteusecase.DeleteAllUseCaseDb
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.deleteusecase.DeleteAllUseCaseDbImpl
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.deleteusecase.DeleteTaskByIdUseCase
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.deleteusecase.DeleteTaskByIdUseCaseImpl
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase.*
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.insertusecase.InsertUseCaseDb
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.insertusecase.InsertUseCaseDbImpl
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.updateusecase.UpdateTaskUseCase
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.updateusecase.UpdateTaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(ViewModelComponent::class)
interface MainModule {

    @Binds
    fun bindInsertUseCase(useCase: InsertUseCaseDbImpl): InsertUseCaseDb

    @Binds
    fun bindGetTasksUseCase(useCase: GetTaskUseCaseImpl): GetTaskUseCaseDb

    @Binds
    fun bindGetTaskByIdUseCase(useCase: GetTaskByIdUseCaseImpl): GetTaskByIdUseCase

    @Binds
    fun bindGetAllTasksOrderByDateUseCase(useCase: GetAllTasksOrderByDateUseCaseImpl): GetAllTasksOrderByDateUseCase

    @Binds
    fun bindDeleteUseCase(useCase: DeleteAllUseCaseDbImpl): DeleteAllUseCaseDb

    @Binds
    fun bindDeleteTaskByIdUseCase(useCase: DeleteTaskByIdUseCaseImpl): DeleteTaskByIdUseCase

    @Binds
    fun bindUpdateTaskUseCase(useCase: UpdateTaskUseCaseImpl): UpdateTaskUseCase
}