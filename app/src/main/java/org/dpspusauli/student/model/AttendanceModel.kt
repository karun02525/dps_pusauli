package org.dpspusauli.student.model
import com.google.gson.annotations.SerializedName


data class AttendanceModel(
    @SerializedName("data")
    var `data`: AttendanceData?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class AttendanceData(
    @SerializedName("analytics")
    var analytics: AttendanceAnalytics?,
    @SerializedName("attlist")
    var attlist: List<Attlist>?,
    @SerializedName("class_id")
    var classId: String?,
    @SerializedName("section")
    var section: String?,
    @SerializedName("teacher_id")
    var teacherId: String?
)

data class AttendanceAnalytics(
    @SerializedName("absent")
    var absent: Int?,
    @SerializedName("holiday")
    var holiday: Int?,
    @SerializedName("leave")
    var leave: Int?,
    @SerializedName("present")
    var present: Int?
)

data class Attlist(
    @SerializedName("att_type")
    var attType: String?,
    @SerializedName("atten_date")
    var attenDate: String?,
    @SerializedName("student_id")
    var studentId: String?
)