package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.FragmentActivitiesBinding
import com.zoltanlorinczi.project_retorfit.databinding.FragmentTaskDetailBinding
import com.zoltanlorinczi.project_retorfit.databinding.SimpleTaskListItemBinding
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.adapter.TasksListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.*
import kotlinx.android.synthetic.main.fragment_task_detail.*
import java.text.SimpleDateFormat

class TaskDetailFragment() : Fragment(R.layout.fragment_task_detail) {


    private lateinit var binding: FragmentTaskDetailBinding
    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var currentTask: TaskResponse



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = TasksViewModelFactory(ThreeTrackerRepository())
        tasksViewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        currentTask = App.selectedForDetail

        val simpleDateFormat = SimpleDateFormat("dd/mm/yyyy")
        val dateString = simpleDateFormat.format(currentTask.deadline)

        binding.detailedDueData.text = dateString
        binding.detailedTaskDescriptionView.text = currentTask.description
        binding.detailedTaskTitleView.text = currentTask.title
        binding.detailedTaskPriorityView.text = currentTask.priority.toString()


        return binding.root
    }
}