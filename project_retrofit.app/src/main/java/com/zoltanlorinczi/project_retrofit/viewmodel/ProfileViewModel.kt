package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.ProfileResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ThreeTrackerRepository): ViewModel() {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    var profile : MutableLiveData<ProfileResponse?> = MutableLiveData()

    var isLoggedIn : MutableLiveData<Boolean> = MutableLiveData()


    init{
        getMyProfile()
    }

    private fun getMyProfile(){
        Log.d("ProfileViewModell", "getMyProfile() called")

        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getMyUser(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get profile response: ${response.body()}")

                    val myProfile = response.body()
                    myProfile?.let {
                        profile.value = it
                        isLoggedIn.value = true
                    }
                } else {
                    Log.d(TAG, "Get user error response: ${response?.errorBody()}")
                    isLoggedIn.value = false
                }

            } catch (e: Exception) {
                Log.d(TAG, "ProfileViewModel - getMyProfile() failed with exception: ${e.message}")
                isLoggedIn.value = false
            }
        }

    }


}