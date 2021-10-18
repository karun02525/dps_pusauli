package org.dpspusauli.student.model
import com.google.gson.annotations.SerializedName


data class LoginParentModel(
    @SerializedName("data")
    var `data`: ParentData?,
    @SerializedName("message")
    var message: String?
)

data class ParentData(
    @SerializedName("parent_id")
    var parentId: String?,
    @SerializedName("token")
    var token: String?
)