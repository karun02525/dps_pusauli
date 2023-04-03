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
import org.dpspusauli.student.model.Parent
import org.dpspusauli.student.model.Student
import org.dpspusauli.student.model.Token

class AuthViewModel(private val restClient: RestClient) : ViewModel() {

    val errorMsg = MutableLiveData<String?>()
    val success = MutableLiveData<String>()
    val dataLiveData = MutableLiveData<Triple<List<Parent>?,List<Student>?, Token?>>()


    fun loginByParent(email: String, password: String) {
        val params = HashMap<String, String>()
        params["email"] = email
        params["password"] = password
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().loginByParentAsync(params).await().let {
                    if (it.isSuccessful) {
                        val data = it.body()!!
                        if (data.userVerified) {
                            dataLiveData.value = Triple(data.payload,data.student,data.token)
                        } else {
                            errorMsg.value = data.message
                        }
                    } else {
                        errorMsg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMsg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }

}
