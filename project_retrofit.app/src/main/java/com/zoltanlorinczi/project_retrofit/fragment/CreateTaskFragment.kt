package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.adapter.DepartmentListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModel

class CreateTaskFragment : Fragment(){

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var createTaskViewModel: CreateTaskViewModel
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CreateTaskViewModelFactory(ThreeTrackerRepository())
        createTaskViewModel = ViewModelProvider(this, factory)[CreateTaskViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_new_task, container, false)

        val titleEditText: EditText = view.findViewById(R.id.taskName)
        val descriptionEditText: EditText = view.findViewById(R.id.taskDescription)
        val button: Button = view.findViewById(R.id.createTaskButton)
        //val spinner: Spinner = view.findViewById(R.id.spinner)

        Log.d(
            TAG,
            "token = " + App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
        )

        button.setOnClickListener {
            Log.d(TAG,"CREATE TASK CLICKED")
            createTaskViewModel.createTask(titleEditText.text.toString(), descriptionEditText.text.toString(), 54, 1, 1625942327, 2, 1)

            createTaskViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d(TAG, "Task created successfully = $it")
                if (it) {
                    findNavController().navigate(R.id.listFragment)
                }
            }
        }

        return view
    }
}