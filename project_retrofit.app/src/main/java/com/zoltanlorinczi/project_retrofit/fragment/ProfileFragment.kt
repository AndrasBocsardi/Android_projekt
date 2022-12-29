package com.zoltanlorinczi.project_retrofit.fragment

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.DepartmentViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.DepartmentViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var departmentViewModel: DepartmentViewModel
    private lateinit var logOutButton: Button
    private lateinit var updateProfileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(ThreeTrackerRepository())

        profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
        departmentViewModel = ViewModelProvider(requireActivity(), DepartmentViewModelFactory(ThreeTrackerRepository()))[DepartmentViewModel::class.java]


    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ProfileFragment","OnCreateView called")

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val myName : TextView = view.findViewById(R.id.myName)
        val email : TextView = view.findViewById(R.id.myEmailTextView)
        val myDepartment : TextView = view.findViewById(R.id.myDepartment)
        val phoneNr : TextView = view.findViewById(R.id.phoneNrTextView)
        val location : TextView = view.findViewById(R.id.locationTextView)
        val profilPicture : ImageView = view.findViewById(R.id.myProfileImage)

        profileViewModel.profile.observe(viewLifecycleOwner){
            Log.d(TAG, "Profile = $it")
            myName.text = profileViewModel.profile.value?.firstName + " " + profileViewModel.profile.value?.lastName
            email.text = profileViewModel.profile.value?.email
            //TODO
            val myDepartmentId = profileViewModel.profile.value?.departmentId

            myDepartment.text = myDepartmentId?.let { it1 ->
                departmentViewModel.getDepartmentNameById(
                    it1
                )
            }


            phoneNr.text = profileViewModel.profile.value?.phoneNumber
            location.text = profileViewModel.profile.value?.location
            Glide
                .with(requireContext())
                .load(profileViewModel.profile.value?.image)
                .override(240,240)
                .fitCenter()
                .into(profilPicture)

        }



        logOutButton = view.findViewById(R.id.profileLogoutButton)
        logOutButton.setOnClickListener { findNavController().navigate(R.id.loginFragment) }

        updateProfileButton = view.findViewById(R.id.profileUpdateButton)
        updateProfileButton.setOnClickListener { findNavController().navigate(R.id.updateProfileFragment) }
        return view
    }

}