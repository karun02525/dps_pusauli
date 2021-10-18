package org.dpspusauli.authenticaton

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import org.dpspusauli.R
import org.dpspusauli.student.model.ParentData
import org.dpspusauli.student.ui.home.StudentDashboardActivity
import org.dpspusauli.utils.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private val spParent by lazy { ParentSharedPref.instance }
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
        val username = edit_username.text.toString()
       // val pass = edit_password.text.toString()
        val pass = username

        when {
            username.isBlank() -> {
                mess("Please enter username")
            }
           /* pass.isBlank() -> {
                mess("Please enter password")
            }*/
            else -> {
                hideShowProgress(true)
                if (loginType == "Parent") {
                    viewModel.loginByParent(username, pass)
                }

            }
        }
    }

    private fun setupViewModel() {
        viewModel.loginStudentData.observe(this, Observer {
            log("TAGS => Parent Login Data:   $it")
            hideShowProgress(false)
            successfullyParentLogin(it)
        })



        viewModel.errorMsg.observe(this, Observer {
            log("TAGS => Parent/Teacher Login Error :   $it")
            hideShowProgress(false)
            toast(it)
        })
    }

    private fun successfullyParentLogin(it: ParentData?) {
        it.let {
            spParent.run {
                isLoginStatus = 1
                parentId = it?.parentId
                authToken = it?.token
                startNewActivityFinish(StudentDashboardActivity::class.java)
            }
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