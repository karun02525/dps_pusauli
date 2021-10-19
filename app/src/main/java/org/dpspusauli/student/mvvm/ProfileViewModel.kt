package org.dpspusauli.student.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.dpspusauli.App
import org.dpspusauli.R
import org.dpspusauli.network.ApiStatus
import org.dpspusauli.network.RestClient
import org.dpspusauli.student.model.ProfileData
import org.dpspusauli.student.model.StudentModel
import org.dpspusauli.student.model.TeacherModel

class ProfileViewModel(private val restClient: RestClient) : ViewModel() {

     val errorMsg = MutableLiveData<String>()
     val success = MutableLiveData<String>()
     val studentData = MutableLiveData<StudentModel>()
     val teacherData = MutableLiveData<TeacherModel>()



    fun getProfileDetails(student_id: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getProfileDetailsAsync(student_id).await().let {
                    if (it.isSuccessful)
                        studentData.value = it.body()!!.data
                     else
                        errorMsg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMsg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }
    fun getTeacher(class_id: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getTeacherAsync(class_id).await().let {
                    if (it.isSuccessful)
                        teacherData.value = it.body()!!.teacher
                     else
                        errorMsg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMsg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }
}
