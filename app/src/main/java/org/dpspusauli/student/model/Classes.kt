package org.dpspusauli.student.model

import com.google.gson.annotations.SerializedName

data class Classes(
    @SerializedName("class_id")
    val classId: String,
    @SerializedName("class_name")
    val className: String
)