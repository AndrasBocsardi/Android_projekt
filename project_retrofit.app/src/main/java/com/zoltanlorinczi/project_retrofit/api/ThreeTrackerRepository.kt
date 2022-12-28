package com.zoltanlorinczi.project_retrofit.api

import RetrofitInstance
import com.zoltanlorinczi.project_retrofit.api.model.*
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
class ThreeTrackerRepository {

    /**
     * 'suspend' keyword means that this function can be blocking.
     * We need to be aware that we can only call them from within a coroutine or an other suspend function!
     */
    suspend fun login(loginRequestBody: LoginRequestBody): Response<LoginResponse> {
        return RetrofitInstance.USER_API_SERVICE.login(loginRequestBody)
    }

    suspend fun getTasks(token: String): Response<List<TaskResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getTasks(token)
    }

    suspend fun getMyUser(token: String): Response<ProfileResponse> {
        return RetrofitInstance.USER_API_SERVICE.getMyUser(token)
    }

    suspend fun createTask(taskRequestBody: TaskRequestBody, token: String): Response<TaskRequestBody> {
        return RetrofitInstance.USER_API_SERVICE.createTask(taskRequestBody, token)
    }

    suspend fun getActivities(token: String): Response<List<ActivityResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getActivities(token)
    }

    suspend fun updateProfile(profileRequestBody: ProfileRequestBody, token: String): Response<ProfileRequestBody>{
        return RetrofitInstance.USER_API_SERVICE.updateProfile(profileRequestBody,token)
    }

}