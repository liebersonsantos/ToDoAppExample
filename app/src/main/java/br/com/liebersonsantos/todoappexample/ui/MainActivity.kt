package br.com.liebersonsantos.todoappexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.com.liebersonsantos.todoappexample.R
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.allTasks.observe(this){
            Timber.tag("LIST").i(it.toString())
            Toast.makeText(this, it.size.toString(), Toast.LENGTH_SHORT).show()
        }

        viewModel.addTask(Task(name = "test"))
    }
}