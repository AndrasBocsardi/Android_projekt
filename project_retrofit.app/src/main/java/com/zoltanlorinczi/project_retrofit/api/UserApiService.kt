package com.zoltanlorinczi.project_retrofit.api

import com.zoltanlorinczi.project_retrofit.api.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Retrofit web service API.
 *
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
interface UserApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequestBody): Response<LoginResponse>

    @GET(BackendConstants.GET_TASKS_URL)
    suspend fun getTasks(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<TaskResponse>>

    @GET(BackendConstants.GET_MY_USER)
    suspend fun getMyUser(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<ProfileResponse>

    @POST(BackendConstants.POST_CREATE_TASK)
    suspend fun createTask(@Body taskRequest : TaskRequestBody, @Header(BackendConstants.HEADER_TOKEN) token: String): Response<TaskRequestBody>

    @GET(BackendConstants.GET_ACTIVITIES_URL)
    suspend fun getActivities(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<ActivityResponse>>

    @POST(BackendConstants.POST_UPDATE_PROFILE)
    suspend fun updateProfile(@Body profileRequest : ProfileRequestBody, @Header(BackendConstants.HEADER_TOKEN) token: String): Response<ProfileRequestBody>

}