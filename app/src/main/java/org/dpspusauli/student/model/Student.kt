package org.dpspusauli.student.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("address")
    val address: String,
    @SerializedName("blood_group")
    val bloodGroup: String,
    @SerializedName("classes")
    val classes: Classes,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("dist")
    val dist: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("father_name")
    val fatherName: String,
    @SerializedName("father_occupation")
    val fatherOccupation: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("mother_name")
    val motherName: String,
    @SerializedName("mother_occupation")
    val motherOccupation: String,
    @SerializedName("parent_doc_no")
    val parentDocNo: String,
    @SerializedName("parent_doc_pdf")
    val parentDocPdf: Any,
    @SerializedName("parent_doc_type")
    val parentDocType: String,
    @SerializedName("parent_email")
    val parentEmail: String,
    @SerializedName("parent_mobile")
    val parentMobile: String,
    @SerializedName("parent_pic")
    val parentPic: Any,
    @SerializedName("pincode")
    val pincode: String,
    @SerializedName("roll_no")
    val rollNo: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("student_doc_no")
    val studentDocNo: String,
    @SerializedName("student_doc_pdf")
    val studentDocPdf: Any,
    @SerializedName("student_doc_type")
    val studentDocType: String,
    @SerializedName("student_id")
    val studentId: String,
    @SerializedName("student_pic")
    val studentPic: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    var isActive:Boolean=false
)