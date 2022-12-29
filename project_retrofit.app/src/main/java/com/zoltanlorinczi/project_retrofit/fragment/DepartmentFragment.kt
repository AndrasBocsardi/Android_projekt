package com.zoltanlorinczi.project_retrofit.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.adapter.DepartmentListAdapter
import com.zoltanlorinczi.project_retrofit.adapter.TasksListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.ProfileResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.*

class DepartmentFragment: Fragment(R.layout.fragment_department_list) {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var departmentViewModel: DepartmentViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var usersViewModel: UsersViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DepartmentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val departmentFactory = DepartmentViewModelFactory(ThreeTrackerRepository())
        val profileFactory = ProfileViewModelFactory(ThreeTrackerRepository())
        val usersFactory = UsersViewModelFactory(ThreeTrackerRepository())

        departmentViewModel = ViewModelProvider(requireActivity(), departmentFactory)[DepartmentViewModel::class.java]
        profileViewModel = ViewModelProvider(requireActivity(), profileFactory)[ProfileViewModel::class.java]
        usersViewModel = ViewModelProvider(requireActivity(), usersFactory)[UsersViewModel::class.java]

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_department_list, container, false)

        recyclerView = view.findViewById(R.id.department_recycler_view)
        setupRecyclerView()

        usersViewModel.users.observe(viewLifecycleOwner) {
            Log.d(TAG, "Users list = $it")

            val myDepartmentId = profileViewModel.profile.value?.departmentId
            val userListByDepartment = myDepartmentId?.let { it1 ->
                usersViewModel.getUsersByDepartmentID(
                    it1
                )
            }

            adapter.setData(userListByDepartment as ArrayList<ProfileResponse>)
            adapter.notifyDataSetChanged()
        }

        return view
    }


    private fun setupRecyclerView() {
        adapter = DepartmentListAdapter(ArrayList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        )
        recyclerView.setHasFixedSize(true)
    }
}