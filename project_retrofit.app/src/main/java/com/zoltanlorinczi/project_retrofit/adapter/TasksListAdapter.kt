package com.zoltanlorinczi.project_retrofit.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.fragment.LoginFragment
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import interfaces.TaskItemClickListener
import java.text.SimpleDateFormat


/**
 * Author:  Zoltan Lorinczi
 * Date:    12/6/2021
 */
class TasksListAdapter(
    private var list: ArrayList<TaskResponse>,
    private val context: Context,
    private val listener: OnItemClickListener,
    private val listener2: OnItemLongClickListener,

) :

    RecyclerView.Adapter<TasksListAdapter.SimpleDataViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    open inner class SimpleDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {

        override fun onClick(v: View?) {

        }

        override fun onLongClick(v: View?): Boolean {
            TODO("Not yet implemented")
        }
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : SimpleDataViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {

        val taskTitleTextView: TextView = itemView.findViewById(R.id.task_title_view)
        val taskDescriptionTextView: TextView = itemView.findViewById(R.id.task_description_view)
        val taskPriorityTextView: TextView = itemView.findViewById(R.id.task_priority_view)
        val dueDateTextView: TextView = itemView.findViewById(R.id.tasks_due_date)



        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)

        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition

           App.selectedForDetail = list[currentPosition]
            itemView.findNavController().navigate(R.id.action_listFragment_to_taskDetailFragment)
        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }
    }


   //  2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleDataViewHolder {
        return when (viewType) {
            TaskListItemType.SIMPLE.value -> {

                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.simple_task_list_item, parent, false)



                SimpleDataViewHolder(itemView)


            }
            TaskListItemType.COMPLEX.value -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.tasks_list_item, parent, false)
                DataViewHolder(itemView)
            }
            else -> {
                throw IllegalStateException("Type is not supported!")
            }
        }

    }



    override fun getItemViewType(position: Int): Int {
        val currentItem = list[position]

        return if (currentItem.status == 0) {
            TaskListItemType.SIMPLE.value
        } else {
            TaskListItemType.COMPLEX.value
        }
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: SimpleDataViewHolder, position: Int) {
        if (getItemViewType(position) == TaskListItemType.COMPLEX.value) {
            val complexHolder = (holder as DataViewHolder)
            val currentItem = list[position]

            val simpleDateFormat = SimpleDateFormat("dd/mm/yyyy")
            val dateString = simpleDateFormat.format(currentItem.deadline)
            complexHolder.taskTitleTextView.text = currentItem.title
            complexHolder.taskDescriptionTextView.text = currentItem.description
            complexHolder.dueDateTextView.text = dateString

            when (currentItem.priority) {
                0 -> {
                    complexHolder.taskPriorityTextView.setBackgroundColor(Color.RED)
                }
                1 -> {
                    complexHolder.taskPriorityTextView.setBackgroundColor(Color.YELLOW)
                }
                2 -> {
                    complexHolder.taskPriorityTextView.setBackgroundColor(Color.GREEN)
                }
            }

        }
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<TaskResponse>) {
        list = newList
    }

    private enum class TaskListItemType(val value: Int) {
        SIMPLE(0),
        COMPLEX(1)
    }
}