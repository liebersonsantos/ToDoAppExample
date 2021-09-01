package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepository
import br.com.liebersonsantos.todoappexample.data.model.Task
import javax.inject.Inject

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class GetAllTasksOrderByDateUseCaseImpl @Inject constructor(private val dbRepository: DbRepository):
    GetAllTasksOrderByDateUseCase {
    override fun invoke(): LiveData<List<Task>> = dbRepository.getAllTasksOrderByDate()
}