package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.updateusecase

import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepository
import br.com.liebersonsantos.todoappexample.data.model.Task
import javax.inject.Inject

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class UpdateTaskUseCaseImpl @Inject constructor(private val dbRepository: DbRepository):
    UpdateTaskUseCase {
    override suspend fun invoke(task: Task) = dbRepository.update(task)
}