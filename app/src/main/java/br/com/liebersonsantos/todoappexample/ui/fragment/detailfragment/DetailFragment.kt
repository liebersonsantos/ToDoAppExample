package br.com.liebersonsantos.todoappexample.ui.fragment.detailfragment

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.liebersonsantos.todoappexample.R
import br.com.liebersonsantos.todoappexample.data.Status
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.databinding.FragmentDetailBinding
import br.com.liebersonsantos.todoappexample.ui.fragment.addtaskfragment.TASK_ID
import br.com.liebersonsantos.todoappexample.ui.fragment.detailfragment.viewmodel.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var selectedStatus: String
    private lateinit var task: Task
    private var taskId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.getLong(TASK_ID)?.let {
            taskId = it
        }
        setToolbar()
        back()
        setStatus()
        observeVMEvents()

        viewModel.start(taskId)

        binding.btnUpdate.setOnClickListener {
            val name = binding.edtTaskDetailName.text.toString()
            if (name.isNotEmpty()){
                val updatedTask = task.copy(name = name, state = selectedStatus)
                update(updatedTask)
                showMessage(getString(R.string.updated_successful))
                findNavController().popBackStack()
            } else {
                showMessage(getString(R.string.name_required))
            }
        }

    }

    private fun update(updatedTask: Task) {
        viewModel.update(updatedTask)
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            binding.btnUpdate,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observeVMEvents() {
        viewModel.task.observe(viewLifecycleOwner){
            task = it
            binding.run {
                edtTaskDetailName.setText(it.name)
                Status.status().forEachIndexed { index, s ->
                    if (s == it.state){
                        selectedStatus = s
                        spinnerTaskDetailStatus.setSelection(index)
                    }
                }
            }
        }
    }

    private fun setStatus() {
        adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_gallery_item,
            Status.status())

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTaskDetailStatus.adapter = adapter

        binding.spinnerTaskDetailStatus.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedStatus = Status.status()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun deleteTask() {
        viewModel.task.removeObservers(viewLifecycleOwner)
        viewModel.delete(task.id)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_task_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_delete -> {
                deleteTask()
                showMessage(getString(R.string.deleted_successful))
                findNavController().popBackStack()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }

    private fun setToolbar() {
        val activity = (activity as AppCompatActivity)
        activity.run {
            setSupportActionBar(binding.toolbarDetail)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_black_24)
            supportActionBar?.title = ""
        }
    }

    private fun back() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}