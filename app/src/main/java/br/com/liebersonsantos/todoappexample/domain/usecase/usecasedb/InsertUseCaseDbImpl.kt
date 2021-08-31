package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb

import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepository
import br.com.liebersonsantos.todoappexample.data.model.Task
import javax.inject.Inject

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class InsertUseCaseDbImpl @Inject constructor(private val dbRepository: DbRepository): InsertUseCaseDb {
    override suspend fun invoke(task: Task) = dbRepository.insert(task)
}