package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.updateusecase

import br.com.liebersonsantos.todoappexample.data.model.Task

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
interface UpdateTaskUseCase {
    suspend operator fun  invoke(task: Task)
}