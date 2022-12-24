package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.FragmentTaskDetailBinding
import com.zoltanlorinczi.project_retorfit.databinding.SimpleTaskListItemBinding
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel

class TaskDetailFragment() : Fragment(R.layout.fragment_task_detail) {


    private lateinit var binding: FragmentTaskDetailBinding
    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var currentTask: TaskResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(ThreeTrackerRepository())
        tasksViewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskDetailBinding.inflate(inflater,container,false)
        currentTask = tasksViewModel.selectedForDetail

        binding.taskDescriptionView.text = currentTask.description
        binding.taskTitleView.text = currentTask.title
        binding.taskPriorityView.text = currentTask.priority.toString()


        return binding.root
    }
}