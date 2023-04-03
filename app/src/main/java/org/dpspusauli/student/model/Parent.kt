package org.dpspusauli.student.model

import com.google.gson.annotations.SerializedName

data class Parent(
    @SerializedName("email")
    val email: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("user_verified")
    val userVerified: Boolean
)



