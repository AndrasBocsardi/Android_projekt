package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModelFactory


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(ThreeTrackerRepository())

        //TODO ITT AD HIBAT
        profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ProfileFragment","OnCreateView called")

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val myName : TextView = view.findViewById(R.id.myName)
        val email : TextView = view.findViewById(R.id.myEmailTextView)



        profileViewModel.profile.observe(viewLifecycleOwner){
            Log.d(TAG, "Profile = $it")
            myName.text = profileViewModel.profile.value?.firstName + " " + profileViewModel.profile.value?.lastName
            email.text = profileViewModel.profile.value?.email
        }


        return view
    }

}