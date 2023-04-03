package org.dpspusauli.student.model


import com.google.gson.annotations.SerializedName

data class StudentDetailsResponse(
    @SerializedName("data")
    val `data`: Student,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)