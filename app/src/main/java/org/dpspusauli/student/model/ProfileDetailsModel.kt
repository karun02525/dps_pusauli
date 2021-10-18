package org.dpspusauli.student.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class ProfileDetailsModel(
    @SerializedName("data")
    var `data`: ProfileData?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class ProfileData(
    @SerializedName("student")
    var student: ProfileStudent?,
    @SerializedName("teacher")
    var teacher: ProfileTeacher?,
    @SerializedName("class_info")
    var class_info: ProfileClassInfo?
)

@Parcelize
data class ProfileStudent(
    @SerializedName("address")
    var address: String?,
    @SerializedName("class_id")
    var classId: String?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("dist")
    var dist: String?,
    @SerializedName("dob")
    var dob: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("father_fname")
    var fatherFname: String?,
    @SerializedName("father_lname")
    var fatherLname: String?,
    @SerializedName("father_sname")
    var fatherSname: String?,
    @SerializedName("fname")
    var fname: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("lname")
    var lname: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("mother_fname")
    var motherFname: String?,
    @SerializedName("mother_lname")
    var motherLname: String?,
    @SerializedName("mother_sname")
    var motherSname: String?,
    @SerializedName("parent_id")
    var parentId: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("pincode")
    var pincode: String?,
    @SerializedName("post_office")
    var postOffice: String?,
    @SerializedName("police_station")
    var policeStation: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("surname")
    var surname: String?,
    @SerializedName("student_picture")
    var student_avatar: String?,
    @SerializedName("parent_picture")
    var parent_avatar: String?,
    @SerializedName("parent_occupation")
    var parentOccupation: String?
):Parcelable


@Parcelize
data class ProfileClassInfo(
    @SerializedName("roll_no")
    var roll_no: String?,
    @SerializedName("section")
    var section: String?,
    @SerializedName("class_name")
    var class_name: String?,
):Parcelable

@Parcelize
data class ProfileTeacher(
    @SerializedName("fname")
    var fname: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("dob")
    var dob: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("lname")
    var lname: String?,
    @SerializedName("surname")
    var surname: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("qualification")
    var qualification: String?,
    @SerializedName("teacher_picture")
    var teacher_avatar: String?
):Parcelable