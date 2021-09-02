package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepository
import br.com.liebersonsantos.todoappexample.data.model.Task
import javax.inject.Inject

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class GetTaskByIdUseCaseImpl @Inject constructor(private val dbRepository: DbRepository):
    GetTaskByIdUseCase {
    override fun invoke(id: Long): LiveData<Task> = dbRepository.getTaskById(id)
}