package br.com.liebersonsantos.todoappexample.ui.fragment.detailfragment.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.deleteusecase.DeleteTaskByIdUseCase
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase.GetTaskByIdUseCase
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.updateusecase.UpdateTaskUseCase
import br.com.liebersonsantos.todoappexample.job.NotificationUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lieberson on 9/1/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val applicationContext: Application,
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskByIdUseCase: DeleteTaskByIdUseCase,
    private val notification: NotificationUtil
): ViewModel() {

    private val _id = MutableLiveData<Long>()
    private val _task: LiveData<Task> = _id.switchMap {
        getTaskByIdUseCase.invoke(it)
    }

    val task = _task

    fun start(id: Long) {
        _id.value = id
    }

    fun update(task: Task) = viewModelScope.launch {
        withContext(ioDispatcher){
            updateTaskUseCase.invoke(task)
        }
    }

    fun delete(id: Long) = viewModelScope.launch {
        withContext(ioDispatcher){
            notification.deleteNotification(applicationContext, id.toInt())
            deleteTaskByIdUseCase.invoke(id = id)
        }
    }
}