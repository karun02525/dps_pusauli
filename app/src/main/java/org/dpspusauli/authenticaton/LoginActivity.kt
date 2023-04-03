package org.dpspusauli.authenticaton

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import org.dpspusauli.R
import org.dpspusauli.student.model.Parent
import org.dpspusauli.student.model.Student
import org.dpspusauli.student.model.Token
import org.dpspusauli.student.ui.home.StudentDashboardActivity
import org.dpspusauli.utils.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private val spParent by lazy { SharedPref.instance }
    private val spTeacher by lazy { TeacherSharedPref.instance }
    private var loginType = "Parent"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupViewModel()
        btnLogin.setOnClickListener {
            checkValidation()
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = group.findViewById(checkedId)
            loginType = radio.text.toString()
        }
    }

    private fun checkValidation() {
        val email = email.text.toString()
        val password = password.text.toString()

        when {
            email.isBlank() -> {
                mess("Please enter email id")
            }
            password.isBlank() -> {
                mess("Please enter password")
            }
            else -> {
                hideShowProgress(true)
                if (loginType == "Parent") {
                    viewModel.loginByParent(email, password)
                }

            }
        }
    }

    private fun setupViewModel() {
        viewModel.dataLiveData.observe(this, Observer {
            log("TAGS => Parent Login Data:   $it")
            hideShowProgress(false)
            loginSuccess(it)
        })



        viewModel.errorMsg.observe(this, Observer {
            log("TAGS => Parent/Teacher Login Error :   $it")
            hideShowProgress(false)
            if (it != null) {
                toast(it)
            }
        })
    }

    private fun loginSuccess(triple: Triple< List<Parent>?,List<Student>?, Token?>) {
        val parentData = triple.first
        val listStudent =  triple.second
        val token = triple.third

       // it.let {
            spParent.run {
                isLoginStatus = 1

               // parentId = it?.parentId
                authToken = token?.access

         //   }

                if(!listStudent.isNullOrEmpty()) {
                    spParent.studentArray = Gson().toJson(listStudent)
                }
                startNewActivityFinish(StudentDashboardActivity::class.java)
        }
    }


    private fun mess(s: String) {
        hideSoftKeyboard()
        messToast(s)
    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }
}