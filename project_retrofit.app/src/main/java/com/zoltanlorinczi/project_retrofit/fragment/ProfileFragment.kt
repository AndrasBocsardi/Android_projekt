package com.zoltanlorinczi.project_retrofit.fragment

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ProfileViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var logOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(ThreeTrackerRepository())

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
        val myJob : TextView = view.findViewById(R.id.myJob)
        val mentorName : TextView = view.findViewById(R.id.mentorName)
        val mentorText : TextView = view.findViewById(R.id.mentorText)
        val phoneNr : TextView = view.findViewById(R.id.phoneNrTextView)
        val location : TextView = view.findViewById(R.id.locationTextView)
        val profilPicture : ImageView = view.findViewById(R.id.myProfileImage)

        profileViewModel.profile.observe(viewLifecycleOwner){
            Log.d(TAG, "Profile = $it")
            myName.text = profileViewModel.profile.value?.firstName + " " + profileViewModel.profile.value?.lastName
            email.text = profileViewModel.profile.value?.email

            //TODO
            myJob.text = "Szamitastechnika"
            mentorName.text = "Lorinczi Zoltan"
            mentorText.text = "Lorinczi Zoltan mentorlatja"

            phoneNr.text = profileViewModel.profile.value?.phoneNumber.toString()
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

        return view
    }

}