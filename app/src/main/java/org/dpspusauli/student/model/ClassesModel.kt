package org.dpspusauli.student.model
import com.google.gson.annotations.SerializedName

data class ClassesModel(
    @SerializedName("_id")
    var id: String,
    @SerializedName("name")
    var name: String?
)