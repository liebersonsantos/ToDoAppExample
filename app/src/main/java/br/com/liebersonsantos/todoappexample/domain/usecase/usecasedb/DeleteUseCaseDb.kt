package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb

import br.com.liebersonsantos.todoappexample.data.model.Task

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
interface DeleteUseCaseDb {
    suspend operator fun invoke(task: Task)
}