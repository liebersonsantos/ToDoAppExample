package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.todoappexample.data.model.Task

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
interface GetTaskUseCaseDb {
    operator fun invoke(): LiveData<List<Task>>
}