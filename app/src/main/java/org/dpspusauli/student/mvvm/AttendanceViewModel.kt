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
import org.dpspusauli.student.model.AttendanceData
import org.dpspusauli.student.model.ProfileData

class AttendanceViewModel(private val restClient: RestClient) : ViewModel() {

     val errorMsg = MutableLiveData<String>()
     val dataAttendance = MutableLiveData<List<AttendanceData>>()


    fun getAttendance(student_id: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getAttendanceAsync(student_id).await().let {
                    if (it.isSuccessful)
                        dataAttendance.value = it.body()!!.data
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
