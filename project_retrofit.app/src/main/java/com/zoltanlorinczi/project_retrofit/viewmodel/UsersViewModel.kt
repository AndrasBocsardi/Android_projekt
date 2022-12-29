package com.zoltanlorinczi.project_retrofit.viewmodel;

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.ProfileResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

public class UsersViewModel(private val repository: ThreeTrackerRepository): ViewModel() {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    val users: MutableLiveData<List<ProfileResponse>> = MutableLiveData()


    init{
        getUsers()
    }

    private fun getUsers(){
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getUsers(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get users response: ${response.body()}")

                    val userList = response.body()
                    userList?.let {
                        users.value = userList
                    }
                } else {
                    Log.d(TAG, "Get users error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "UsersViewModel - getUsers() failed with exception: ${e.message}")
            }
        }
    }

   fun getUsersByDepartmentID(id: Int): List<ProfileResponse?>? {
        getUsers()
        return users.value?.filter { it.departmentId  == id  }
    }

//    fun getUserNames(): List<String>{
//        val usersList: List<ProfileResponse> = users.value!!
//
//        val names List<String>
//            if(usersList != null){
//                for (user in usersList){
//                    (user.firstName +" " + user.lastName)
//                }
//            }
//
//    }
}
