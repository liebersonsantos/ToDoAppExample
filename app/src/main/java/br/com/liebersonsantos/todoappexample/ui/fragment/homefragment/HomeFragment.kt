package br.com.liebersonsantos.todoappexample.ui.fragment.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.liebersonsantos.todoappexample.R
import br.com.liebersonsantos.todoappexample.databinding.FragmentHomeBinding
import br.com.liebersonsantos.todoappexample.ui.fragment.homefragment.adapter.HomeAdapter
import br.com.liebersonsantos.todoappexample.ui.fragment.homefragment.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        setAdapter()
        setRecyclerView()
        observeVMEvents()
        addTask()

    }

    private fun observeVMEvents(){
        viewModel.allTasks.observe(viewLifecycleOwner){ tasks ->
            Timber.tag("LIST").i(tasks.toString())
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

        }
    }

    private fun addTask() {
        binding.fabAddTask.setOnClickListener {
            findNavController()
                .navigate(R.id.action_homeFragment_to_addTaskFragment)
        }
    }
}