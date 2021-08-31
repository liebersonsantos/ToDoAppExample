package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepository
import br.com.liebersonsantos.todoappexample.data.model.Task
import javax.inject.Inject

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class GetTaskUseCaseImpl @Inject constructor(private val dbRepository: DbRepository): GetTaskUseCaseDb {
    override fun invoke(): LiveData<List<Task>> = dbRepository.getAllTasks()
}