package com.zoltanlorinczi.project_retrofit.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
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
import com.zoltanlorinczi.project_retrofit.viewmodel.*
import java.text.SimpleDateFormat
import java.util.Date

class CreateTaskFragment : Fragment(){

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var createTaskViewModel: CreateTaskViewModel
    private lateinit var usersViewModel: UsersViewModel
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val createTaskFactory = CreateTaskViewModelFactory(ThreeTrackerRepository())
        val usersFactory = UsersViewModelFactory(ThreeTrackerRepository())
        val profileFactory = ProfileViewModelFactory(ThreeTrackerRepository())
        createTaskViewModel = ViewModelProvider(this, createTaskFactory)[CreateTaskViewModel::class.java]
        profileViewModel = ViewModelProvider(this, profileFactory)[ProfileViewModel::class.java]
        usersViewModel = ViewModelProvider(this, usersFactory)[UsersViewModel::class.java]
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
        val assignedToUserID: EditText = view.findViewById(R.id.editTextAssignedToUserId)
        val calendar: CalendarView = view.findViewById(R.id.calendarView)
        val departmentId = profileViewModel.getMyDepartmentId()
        var mydate: Date = Date()
        var dateString: String
        val dateFormat = SimpleDateFormat("dd/mm/yyyy")
        var dateLong: Long = 0
        //val spinner: Spinner = view.findViewById(R.id.spinner)

        calendar.setOnDateChangeListener{view, year, month, dayOfMonth ->
            dateString = dayOfMonth.toString()+"/"+month+1.toString()+"/"+year.toString()
            mydate = dateFormat.parse(dateString) as Date
            //Log.d(TAG, dateString)

            Log.d("CALENDAR","${mydate.time}  $dateString")
        }

        Log.d(
            TAG,
            "token = " + App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
        )

        button.setOnClickListener {
            Log.d(TAG,"CREATE TASK CLICKED")




            createTaskViewModel.createTask(titleEditText.text.toString(), descriptionEditText.text.toString(), assignedToUserID.text.toString().toInt(), 1, mydate.time, 2, 1)

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