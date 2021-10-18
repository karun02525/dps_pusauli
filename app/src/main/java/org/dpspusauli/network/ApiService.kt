package org.dpspusauli.network

import kotlinx.coroutines.Deferred
import org.dpspusauli.student.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    // Authentication
    @POST("/api/student/auth/login")
    fun loginByParentAsync(@Body param: HashMap<String, String>): Deferred<Response<LoginParentModel>>

    /*@POST("/api/teacher/auth/login")
    fun loginByTeacherAsync(@Body param: HashMap<String, String>): Deferred<Response<LoginTeacherModel>>
*/

    @GET("/api/student/get-profile")
    fun getProfileDetailsAsync(@Query("student_id") student_id: String): Deferred<Response<ProfileDetailsModel>>

    @GET("/api/student/get-attendance")
    fun getAttendanceAsync(@Query("student_id") student_id: String): Deferred<Response<AttendanceModel>>

    @GET("/api/student-parent")
    fun getStudentsWithParentIdAsync(@Query("parent_doc_id") parent_doc_id: String): Deferred<Response<ParentModel>>



}