package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class ActivityResponse(
    @SerializedName("ID")
    var id: Int,

    @SerializedName("type")
    var type: Int,

    @SerializedName("created_by_user_id")
    var createdBy: Int,

    @SerializedName("sub_type")
    var subType: Int,

    @SerializedName("created_time")
    var createdTime: Long,

    @SerializedName("sub_ID")
    var subId: Int,

    @SerializedName("sub_user_ID")
    var subUserId: Int,

    @SerializedName("note")
    var note: String,

    @SerializedName("progress")
    var progress: String,
)
