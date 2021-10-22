package org.dpspusauli.student.model
import com.google.gson.annotations.SerializedName


data class AttendanceModel(
    @SerializedName("data")
    var `data`: List<AttendanceData>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Boolean
)

data class AttendanceData(
    @SerializedName("createdAt")
    var createdAt: String,
    @SerializedName("type")
    var type: String
)
