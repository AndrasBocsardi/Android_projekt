package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.LoginRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.TaskRequestBody
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateTaskViewModel(private val repository: ThreeTrackerRepository): ViewModel() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun createTask(
        title: String,
        description: String,
        assignedToUserId: Int,
        priority: Int,
        deadline: Int,
        departmentId: Int,
        status: Int
    ) {
        val requestBody = TaskRequestBody(
            title,
            description,
            assignedToUserId,
            priority,
            deadline,
            departmentId,
            status
        )
        viewModelScope.launch {
            executeCreateTask(requestBody)
        }
    }

    private suspend fun executeCreateTask(requestBody: TaskRequestBody) {
        try {
            val token: String? = App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token"
            )
            val response = token?.let {
                repository.createTask(requestBody, token)
            }

            if (response != null) {
                if (response.isSuccessful) {
                    Log.d(TAG, "CreateTask response: ${response.body()}")
                    isSuccessful.value = true
                } else {
                    Log.d(TAG, "CreateTask error response: ${response.message()}")
                    isSuccessful.value = false
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "CreateTaskViewModel - createTask() failed with exception: ${e.message}")
            isSuccessful.value = false
        }
    }
}