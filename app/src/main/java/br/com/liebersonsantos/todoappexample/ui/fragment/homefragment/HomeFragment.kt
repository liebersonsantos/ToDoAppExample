package br.com.liebersonsantos.todoappexample.ui.fragment.homefragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.liebersonsantos.todoappexample.R
import br.com.liebersonsantos.todoappexample.data.FilterIntent
import br.com.liebersonsantos.todoappexample.databinding.FragmentHomeBinding
import br.com.liebersonsantos.todoappexample.ui.fragment.addtaskfragment.TASK_ID
import br.com.liebersonsantos.todoappexample.ui.fragment.homefragment.adapter.HomeAdapter
import br.com.liebersonsantos.todoappexample.ui.fragment.homefragment.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setToolbar()

        setAdapter()
        setRecyclerView()
        observeVMEvents()
        addTask()

        viewModel.filter(FilterIntent.ASC)

    }

    private fun observeVMEvents(){
        viewModel.allTasks.observe(viewLifecycleOwner){ tasks ->
            homeAdapter.submitList(tasks)
        }
    }

    private fun setRecyclerView(){
        binding.rvTaskList.run {
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }

    private fun setAdapter(){
        homeAdapter = HomeAdapter { task ->
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,
                Bundle().apply {
                    putLong(TASK_ID, task.id)
                })
        }
    }

    private fun addTask() {
        binding.fabAddTask.setOnClickListener {
            findNavController()
                .navigate(R.id.action_homeFragment_to_addTaskFragment)
        }
    }

    private fun setToolbar() {
        val activity = (activity as AppCompatActivity)
        activity.run {
            setSupportActionBar(binding.toolbarHome)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_black_24)
            supportActionBar?.title = ""
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_task, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_delete_all -> viewModel.deleteAll()
            R.id.action_order_date -> viewModel.filter(FilterIntent.DATE)
            R.id.action_order_abc -> viewModel.filter(FilterIntent.ASC)
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }
}