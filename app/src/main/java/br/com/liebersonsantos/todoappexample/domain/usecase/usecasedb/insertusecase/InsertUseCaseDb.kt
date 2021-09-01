package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.insertusecase

import br.com.liebersonsantos.todoappexample.data.model.Task

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
interface InsertUseCaseDb {
    suspend operator fun invoke(task: Task)
}