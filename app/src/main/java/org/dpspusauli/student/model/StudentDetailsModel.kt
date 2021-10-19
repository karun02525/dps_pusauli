package org.dpspusauli.student.model

import com.google.gson.annotations.SerializedName

data class StudentDetailsModel(
    @SerializedName("data")
    var `data`:StudentModel,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Boolean
)


