package br.com.liebersonsantos.todoappexample.ui.fragment.addtaskfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.insertusecase.InsertUseCaseDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lieberson on 9/1/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class AddTaskViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val insertUseCase: InsertUseCaseDb
): ViewModel() {

    private val _insert = MutableLiveData<Long>()
    val insert: LiveData<Long>
    get() = _insert

    fun addTask(task: Task){
        viewModelScope.launch {
            try {
                val taskId = withContext(ioDispatcher){
                    insertUseCase.invoke(task)
                }

                _insert.value = taskId
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }


}