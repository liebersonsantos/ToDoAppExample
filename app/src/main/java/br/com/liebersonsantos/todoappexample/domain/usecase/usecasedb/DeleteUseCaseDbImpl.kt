package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb

import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepository
import br.com.liebersonsantos.todoappexample.data.model.Task
import javax.inject.Inject

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class DeleteUseCaseDbImpl @Inject constructor(private val dbRepository: DbRepository): DeleteUseCaseDb {
    override suspend fun invoke(task: Task) = dbRepository.deleteTask(task)
}