package br.com.liebersonsantos.todoappexample.ui.fragment.addtaskfragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import br.com.liebersonsantos.todoappexample.R
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.data.model.TaskDate
import br.com.liebersonsantos.todoappexample.databinding.FragmentAddTaskBinding
import br.com.liebersonsantos.todoappexample.job.NotificationWorkManager
import br.com.liebersonsantos.todoappexample.ui.fragment.addtaskfragment.viewmodel.AddTaskViewModel
import br.com.liebersonsantos.todoappexample.ui.fragment.picker.DatePickerFragment
import br.com.liebersonsantos.todoappexample.ui.fragment.picker.TimePickerFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

const val TIME_PICKER = "TIME_PICKER"
const val DATE_PICKER = "DATE_PICKER"
const val TASK_NAME = "TASK_NAME"
const val TASK_ID = "TASK_ID"

@AndroidEntryPoint
class AddTaskFragment : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: FragmentAddTaskBinding
    private val viewModel: AddTaskViewModel by viewModels()
    private var taskDate = TaskDate()
    private lateinit var task: Task

    private val workManager = WorkManager.getInstance(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()

        viewModel.insert.observe(viewLifecycleOwner){ taskId ->
            if (taskDate.isDateReady() && taskDate.isTimeReady()) {

            }
        }


    }

    private fun setListener() {
        binding.btnTaskNewSave.setOnClickListener {
            val name = binding.edtTaskNewName.text.toString()
            if (name.isNotEmpty()) {
                task = Task(name = name, date = setTaskDateTime(taskDate))
                viewModel.addTask(task)
            } else {
                Snackbar.make(
                    binding.edtTaskNewName, getString(R.string.name_required),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        binding.txtTaskNewDate.setOnClickListener {
            datePickerDialog()
        }

        binding.txtTaskNewTime.setOnClickListener {
            timePickerDialog()
        }
    }

    private fun timePickerDialog() {
        val newFragment: DialogFragment = TimePickerFragment.newInstance(this)
        newFragment.show(parentFragmentManager, TIME_PICKER)
    }

    private fun datePickerDialog() {
        val newFragment: DialogFragment = DatePickerFragment.newInstance(this)
        newFragment.show(parentFragmentManager, DATE_PICKER)
    }

    private fun workManager(task: Task) {
        val timeTilFuture = ChronoUnit.MILLIS.between(OffsetDateTime.now(), task.date)
        val data = Data.Builder()
        data.putString(TASK_NAME, task.name)
        data.putLong(TASK_ID, task.id)

        val workRequest = OneTimeWorkRequest.Builder(NotificationWorkManager::class.java)
            .setInitialDelay(timeTilFuture, TimeUnit.MILLISECONDS)
            .setInputData(data.build())
            .addTag(task.id.toString())
            .build()

        workManager.enqueue(workRequest)
    }

    private fun setTaskDateTime(taskDate: TaskDate): OffsetDateTime? {
        return taskDate.takeIf {
            it.isTimeReady() && it.isDateReady() }?.let {
            OffsetDateTime.of(
                it.year,
                it.month,
                it.day,
                it.hour,
                it.minute,
                0,
                0,
                OffsetDateTime.now().offset
            )
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        taskDate = taskDate.copy(day = dayOfMonth, month = month + 1, year = year)
        binding.txtTaskNewDate.text = "$dayOfMonth/${this.taskDate.month}/$year"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        taskDate = taskDate.copy(minute = minute, hour = hourOfDay)
        binding.txtTaskNewTime.text = "$hourOfDay:$minute"
    }
}