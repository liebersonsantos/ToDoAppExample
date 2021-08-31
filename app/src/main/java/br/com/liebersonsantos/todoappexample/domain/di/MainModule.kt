package br.com.liebersonsantos.todoappexample.domain.di

import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(ViewModelComponent::class)
interface MainModule {

    @Binds
    fun bindGetTasksUseCase(useCase: GetTaskUseCaseImpl): GetTaskUseCaseDb

    @Binds
    fun bindInsertUseCase(useCase: InsertUseCaseDbImpl): InsertUseCaseDb

    @Binds
    fun bindDeleteUseCase(useCase: DeleteUseCaseDbImpl): DeleteUseCaseDb
}