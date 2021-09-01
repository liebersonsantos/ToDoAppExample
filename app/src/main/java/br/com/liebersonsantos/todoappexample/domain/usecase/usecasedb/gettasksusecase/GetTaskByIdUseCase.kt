package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.todoappexample.data.model.Task

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
interface GetTaskByIdUseCase {
    operator fun invoke(id: Int): LiveData<Task>
}