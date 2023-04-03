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
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("status")
    var status: String
)
