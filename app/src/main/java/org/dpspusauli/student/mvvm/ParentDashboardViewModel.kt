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
import org.dpspusauli.student.model.ParentDashboardData
import org.dpspusauli.student.model.StudentModel

class ParentDashboardViewModel(private val restClient: RestClient) : ViewModel() {

     val errorMsg = MutableLiveData<String>()
     val dashboardData = MutableLiveData<ParentDashboardData>()
     val studentList = MutableLiveData<List<StudentModel>>()


    fun getStudentsWithParentId(parent_doc_id: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getStudentsWithParentIdAsync(parent_doc_id).await().let {
                    if (it.isSuccessful)
                        studentList.value = it.body()!!.data
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
