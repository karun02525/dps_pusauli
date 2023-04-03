package org.dpspusauli.network

import kotlinx.coroutines.Deferred
import org.dpspusauli.student.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Authentication
    @POST("/api/login/")
    fun loginByParentAsync(@Body param: HashMap<String, String>): Deferred<Response<LoginParentModel>>

    /*@POST("/api/teacher/auth/login")
    fun loginByTeacherAsync(@Body param: HashMap<String, String>): Deferred<Response<LoginTeacherModel>>
*/


    @GET("/api/student-attendance/{student_id}")
    fun getAttendanceAsync(@Path("student_id") student_id: String): Deferred<Response<AttendanceModel>>

    @GET("/api/student-info")
    fun getStudentsWithParentIdAsync(@Query("parent_doc_id") parent_doc_id: String): Deferred<Response<ParentModel>>

    @GET("/api/students/{student_id}")
    fun getProfileDetailsAsync(@Path("student_id") student_id: String): Deferred<Response<StudentDetailsResponse>>

    @GET("/api/teacher")
    fun getTeacherAsync(@Query("class_id") class_id: String): Deferred<Response<TeacherModelInfo>>


}