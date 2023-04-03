package org.dpspusauli.student.model
import com.google.gson.annotations.SerializedName


data class LoginParentModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("payload")
    val payload: List<Parent>?= emptyList(),
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("student")
    val student: List<Student>?= emptyList(),
    @SerializedName("token")
    val token: Token?=null,
    @SerializedName("user_verified")
    val userVerified: Boolean
)



