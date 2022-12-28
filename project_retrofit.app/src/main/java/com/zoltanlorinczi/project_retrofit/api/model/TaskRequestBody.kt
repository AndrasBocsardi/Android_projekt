package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class TaskRequestBody(
    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("assignedToUserId")
    var assignedToUserId: Int,

    @SerializedName("priority")
    var priority: Int,

    @SerializedName("deadline")
    var deadline: Int,

    @SerializedName("departmentId")
    var departmentId: Int,

    @SerializedName("status")
    var status: Int
)


