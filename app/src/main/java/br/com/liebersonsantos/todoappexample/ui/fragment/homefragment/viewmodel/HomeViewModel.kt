package br.com.liebersonsantos.todoappexample.ui.fragment.homefragment.viewmodel

import androidx.lifecycle.*
import br.com.liebersonsantos.todoappexample.data.FilterIntent
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.deleteusecase.DeleteAllUseCaseDb
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase.GetAllTasksOrderByDateUseCase
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase.GetTaskUseCaseDb
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.insertusecase.InsertUseCaseDb
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.updateusecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val getTaskUseCaseDb: GetTaskUseCaseDb,
    private val getAllTasksOrderByDateUseCase: GetAllTasksOrderByDateUseCase,
    private val deleteAllUseCaseDb: DeleteAllUseCaseDb
) : ViewModel() {

    private val _filter = MutableLiveData<FilterIntent>()
    private val _allTasks: LiveData<List<Task>> = _filter.switchMap {
        when (it) {
            FilterIntent.ASC -> getTaskUseCaseDb.invoke()
            FilterIntent.DATE -> getAllTasksOrderByDateUseCase.invoke()
            else -> throw IllegalArgumentException("Unknown filter option $it")
        }
    }

    val allTasks: LiveData<List<Task>> = _allTasks

    fun filter(intent: FilterIntent) {
        _filter.value = intent
    }

    fun deleteAll() = viewModelScope.launch {
        withContext(ioDispatcher) {
            deleteAllUseCaseDb.invoke()
        }
    }
}