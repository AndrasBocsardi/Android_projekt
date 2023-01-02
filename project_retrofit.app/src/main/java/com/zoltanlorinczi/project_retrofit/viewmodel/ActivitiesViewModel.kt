package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.ActivityResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class ActivitiesViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var products: MutableLiveData<List<ActivityResponse>?> = MutableLiveData()

    init {
        getActivities()
    }

    private fun getActivities() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getActivities(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get activities response: ${response.body()}")

                    val activitiesList = response.body()
                    activitiesList?.let {
                        products.value = activitiesList
                    }
                } else {
                    Log.d(TAG, "Get activities error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "TasksViewModel - getActivities() failed with exception: ${e.message}")
            }
        }
    }
}