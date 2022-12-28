package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.DepartmentResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class DepartmentViewModel(private val repository: ThreeTrackerRepository): ViewModel() {
    companion object {
        val TAG: String = javaClass.simpleName
    }

    val departments: MutableLiveData<List<DepartmentResponse>> = MutableLiveData()

    init {
        getDepartments()
    }

    private fun getDepartments(){
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getDepartments(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get departments response: ${response.body()}")

                    val departmentList = response.body()
                    departmentList?.let {
                        departments.value = departmentList
                    }
                } else {
                    Log.d(TAG, "Get departments error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "DepartmentViewModel - getDepartments() failed with exception: ${e.message}")
            }
        }
    }

//    fun getDepartmentNameById(id: Int): String {
//        return departments.value?.get(id)?.departmentName ?: "No department name found"
//    }

    fun getDepartmentNameById(id: Int): String? {
        val department = departments.value?.firstOrNull { it.departmentId == id }
        return department?.departmentName
    }
}




