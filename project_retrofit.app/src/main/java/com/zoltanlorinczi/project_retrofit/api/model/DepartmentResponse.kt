package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class DepartmentResponse(
    @SerializedName("ID")
    var departmentId: Int,
    @SerializedName("name")
    var departmentName: String,

)
