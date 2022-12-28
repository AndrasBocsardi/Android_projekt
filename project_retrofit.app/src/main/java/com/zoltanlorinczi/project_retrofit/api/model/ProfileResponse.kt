package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName


data class ProfileResponse (
    @SerializedName("ID")
    var id: Int,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("type")
    var type: Int,

    @SerializedName("location")
    var location: String,

    @SerializedName("phone_number")
    var phoneNumber: Int,

    @SerializedName("department_id")
    var departmentId: Int,

    @SerializedName("image")
    var image: String,


        )