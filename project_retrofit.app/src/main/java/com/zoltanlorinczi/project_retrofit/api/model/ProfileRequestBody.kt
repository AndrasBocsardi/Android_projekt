package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class ProfileRequestBody(

    @SerializedName("lastName")
    var lastName: String,

    @SerializedName("firstName")
    var firstName: String,

    @SerializedName("location")
    var location: String,

    @SerializedName("phoneNumber")
    var phoneNumber: Int,

    @SerializedName("imageUrl")
    var imageUrk: String,
)