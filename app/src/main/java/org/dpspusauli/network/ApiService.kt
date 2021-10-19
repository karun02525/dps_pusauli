package org.dpspusauli.network

import kotlinx.coroutines.Deferred
import org.dpspusauli.student.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Authentication
    @POST("/api/student/auth/login")
    fun loginByParentAsync(@Body param: HashMap<String, String>): Deferred<Response<LoginParentModel>>

    /*@POST("/api/teacher/auth/login")
    fun loginByTeacherAsync(@Body param: HashMap<String, String>): Deferred<Response<LoginTeacherModel>>
*/


    @GET("/api/student/get-attendance")
    fun getAttendanceAsync(@Query("student_id") student_id: String): Deferred<Response<AttendanceModel>>




    @GET("/api/student-parent")
    fun getStudentsWithParentIdAsync(@Query("parent_doc_id") parent_doc_id: String): Deferred<Response<ParentModel>>

    @GET("/api/student/{student_id}")
    fun getProfileDetailsAsync(@Path("student_id") student_id: String): Deferred<Response<StudentDetailsModel>>

    @GET("/api/teacher")
    fun getTeacherAsync(@Query("class_id") class_id: String): Deferred<Response<TeacherModelInfo>>


}