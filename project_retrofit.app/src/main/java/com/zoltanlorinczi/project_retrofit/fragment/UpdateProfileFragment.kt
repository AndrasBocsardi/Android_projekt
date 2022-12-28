package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.UpdateProfileViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.UpdateProfileViewModelFactory

class UpdateProfileFragment:Fragment() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var updateProfileViewModel: UpdateProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UpdateProfileViewModelFactory(ThreeTrackerRepository())
        updateProfileViewModel = ViewModelProvider(this, factory)[UpdateProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_update_profile, container, false)

        val lastName: EditText = view.findViewById(R.id.editTextLastName)
        val firstName: EditText = view.findViewById(R.id.editTextFirstName)
        val location: EditText = view.findViewById(R.id.editTextLocation)
        val phoneNr: EditText = view.findViewById(R.id.editTextPhoneNumber)
        val imgUrl: EditText = view.findViewById(R.id.editTextImageUrl)

        val button: Button = view.findViewById(R.id.buttonUpdateProfile)

        Log.d(
            TAG,
            "token = " + App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
        )

        button.setOnClickListener {
            Log.d(TAG,"UpdateProfileClicked")

            updateProfileViewModel.updateProfile(lastName.text.toString(), firstName.text.toString(),location.text.toString(),phoneNr.text.toString(),imgUrl.text.toString())

            updateProfileViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d(TAG, "Profile updated successfully = $it")
                if (it) {
                    findNavController().navigate(R.id.profileFragment)
                }
            }
        }

        return view
    }
}
