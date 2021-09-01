package br.com.liebersonsantos.todoappexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase.GetTaskUseCaseImpl
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.insertusecase.InsertUseCaseDbImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class TaskViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val insertUseCase: InsertUseCaseDbImpl,
    private val getTaskUseCaseDb: GetTaskUseCaseImpl
): ViewModel() {

    val allTasks: LiveData<List<Task>> = getTaskUseCaseDb.invoke()

    fun addTask(task: Task){
        viewModelScope.launch {
            insertUseCase.invoke(task)
        }
    }

}