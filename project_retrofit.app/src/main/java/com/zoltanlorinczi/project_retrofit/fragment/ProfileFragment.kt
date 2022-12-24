package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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
            phoneNr.text = "0777777777"
            location.text = "Sepsiszentgyorgy"
            Glide
                .with(requireContext())
                .load("https://flyinryanhawks.org/wp-content/uploads/2016/08/profile-placeholder.png")
                .override(240,240)
                .fitCenter()
                .into(profilPicture)
        }


        return view
    }

}