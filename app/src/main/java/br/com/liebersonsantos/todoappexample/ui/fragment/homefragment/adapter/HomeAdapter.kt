package br.com.liebersonsantos.todoappexample.ui.fragment.homefragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.liebersonsantos.todoappexample.data.Status
import br.com.liebersonsantos.todoappexample.data.model.Task
import br.com.liebersonsantos.todoappexample.databinding.ItemTaskBinding
import br.com.liebersonsantos.todoappexample.extensions.converterToDate

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class HomeAdapter(
    private val clickedItem: ((item: Task) -> Unit)):
    ListAdapter<Task, HomeAdapter.AdapterViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(itemBinding, clickedItem)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class AdapterViewHolder(
        private val itemBinding: ItemTaskBinding,
        private val clickedItem: (item: Task) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(task: Task) {
            itemBinding.run {
                txtItemTaskName.text = task.name
                txtItemTaskDate.text = (task.date?.converterToDate()?.getDateTimeSet() ?: "").toString()
                txtItemTaskStatus.text = task.state
                txtItemTaskStatus.setStatus(Status.safeValueOf(task.state))

                itemView.setOnClickListener {
                    clickedItem.invoke(task)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }

        }
    }
}
