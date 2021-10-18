package org.dpspusauli.student.model
import com.google.gson.annotations.SerializedName


data class ParentModel(
    @SerializedName("data")
    var `data`: List<StudentModel>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Boolean
)


