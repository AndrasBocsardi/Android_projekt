package com.zoltanlorinczi.project_retrofit.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.DepartmentViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.DepartmentViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModelFactory


class MyGroupsFragment : Fragment(R.layout.fragment_my_groups) {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var departmentViewModel: DepartmentViewModel

    private lateinit var showMembersButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(ThreeTrackerRepository())

        profileViewModel = ViewModelProvider(requireActivity(), factory)[ProfileViewModel::class.java]
        departmentViewModel = ViewModelProvider(requireActivity(), DepartmentViewModelFactory(
            ThreeTrackerRepository()
        )
        )[DepartmentViewModel::class.java]
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_my_groups, container, false)

        val myDepartmentName: TextView = view.findViewById(R.id.myGroupName)

        departmentViewModel.departments.observe(viewLifecycleOwner){
            Log.d(TAG, "Departmtn = $it")

            val myDepartmentId = profileViewModel.profile.value?.departmentId
            myDepartmentName.text = myDepartmentId?.let { it1 ->
                departmentViewModel.getDepartmentNameById(
                    it1
                )
            }
        }

        showMembersButton = view.findViewById(R.id.showGroupMembersButton)
        showMembersButton.setOnClickListener { findNavController().navigate(R.id.departmentFragment) }

        return view
    }


}