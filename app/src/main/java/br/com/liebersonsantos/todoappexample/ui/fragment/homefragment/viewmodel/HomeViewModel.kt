package br.com.liebersonsantos.todoappexample.ui.fragment.homefragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.gettasksusecase.GetTaskUseCaseDb
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.insertusecase.InsertUseCaseDb
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
    private val getTaskUseCaseDb: GetTaskUseCaseDb
): ViewModel() {

    val allTasks: LiveData<List<Task>> = getTaskUseCaseDb.invoke()

}