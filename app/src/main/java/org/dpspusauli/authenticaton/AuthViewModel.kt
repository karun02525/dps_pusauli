package org.dpspusauli.authenticaton

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.dpspusauli.App
import org.dpspusauli.R
import org.dpspusauli.network.ApiStatus
import org.dpspusauli.network.RestClient
import org.dpspusauli.student.model.ParentData

class AuthViewModel(private val restClient: RestClient) : ViewModel() {

     val errorMsg = MutableLiveData<String>()
     val success = MutableLiveData<String>()
     val loginStudentData = MutableLiveData<ParentData>()



    fun loginByParent(id: String, pass:String) {
        val params= HashMap<String, String>()
        params["parent_id"] = id
        params["password"] = pass
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().loginByParentAsync(params).await().let {
                    if (it.isSuccessful)
                        loginStudentData.value = it.body()!!.data!!
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
