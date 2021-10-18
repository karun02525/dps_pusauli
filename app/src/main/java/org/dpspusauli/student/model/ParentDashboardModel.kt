package org.dpspusauli.student.model
import com.google.gson.annotations.SerializedName


data class ParentDashboardModel(
    @SerializedName("data")
    var `data`: ParentDashboardData?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class ParentDashboardData(
    @SerializedName("banner")
    var banner: Any?,
    @SerializedName("students")
    var students: List<DashboardStudent>?
)

data class DashboardStudent(
    @SerializedName("class_id")
    var classId: String?,
    @SerializedName("class_name")
    var className: String?,
    @SerializedName("fname")
    var fname: String?,
    @SerializedName("_id")
    var id: String?,
    @SerializedName("lname")
    var lname: String?,
    @SerializedName("roll_no")
    var rollNo: String?,
    @SerializedName("section")
    var section: String?,
    @SerializedName("student_picture")
    var studentPicture: String?,
    @SerializedName("surname")
    var surname: String?,
    var isActive:Boolean=false
)