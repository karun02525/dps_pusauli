package org.dpspusauli.utils

import android.content.Context
import org.dpspusauli.App


class SharedPref private constructor() {

    companion object {
        val instance = SharedPref()
        private const val preference_file_key = "org.dpspusauli_preference"

        private const val LANGUAGE = "language"
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val PARENT_ID = "parent_id"
        private const val STUDENT_ARRAY = "stu_array"

        private val USER_PASSWORD = "user_password"
        private val USER_NAME = "user_name"
        private val GENDER = "gender"
        private val MOBILE_NUMBER = "mobile_number"
        private val EarningPercentage = "earning_percentage"
        private val EMAIL_ID = "email_id"
        private val PROFILE_AVATAR = "profile_picture_url"
        private val AUTH_TOKEN = "auth_token"
        private val RefreshToken_TOKEN = "refreshToken"
        private val Employee_code = "Employee_code"
        private val Branch = "branch"
        private val Region = "region"
        private val Email = "email"
        private val Partners = "Partners"
    }


    private val sharedPref =
        App.appContext!!.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()


    var isLoginStatus: Int
        get() = sharedPref.getInt(IS_LOGGED_IN, 0)
        set(isLoginStatus) {
            editor.putInt(IS_LOGGED_IN, isLoginStatus)
            editor.apply()
        }


    var parentId: String?
        get() = sharedPref.getString(PARENT_ID, "")
        set(parentId) {
            editor.putString(PARENT_ID, parentId)
            editor.apply()
        }
    var studentArray: String?
        get() = sharedPref.getString(STUDENT_ARRAY, "")
        set(studentArray) {
            editor.putString(STUDENT_ARRAY, studentArray)
            editor.apply()
        }


    var employee_code: String?
        get() = sharedPref.getString(Employee_code, "")
        set(employee_code) {
            editor.putString(Employee_code, employee_code)
            editor.apply()
        }

    var branch: String?
        get() = sharedPref.getString(Branch, "")
        set(branch) {
            editor.putString(Branch, branch)
            editor.apply()
        }

    var region: String?
        get() = sharedPref.getString(Region, "")
        set(region) {
            editor.putString(Region, region)
            editor.apply()
        }

    var email: String?
        get() = sharedPref.getString(Email, "")
        set(email) {
            editor.putString(Email, email)
            editor.apply()
        }

    var partners: String?
        get() = sharedPref.getString(Partners, "")
        set(partners) {
            editor.putString(Partners, partners)
            editor.apply()
        }

    var userName: String?
        get() = sharedPref.getString(USER_NAME, "")
        set(userName) {
            editor.putString(USER_NAME, userName)
            editor.apply()
        }

    var checkLanguage: String?
        get() = sharedPref.getString(LANGUAGE, "")
        set(language) {
            editor.putString(LANGUAGE, language)
            editor.apply()
        }

    var mobileNumber: String?
        get() = sharedPref.getString(MOBILE_NUMBER, "")
        set(mobile) {
            editor.putString(MOBILE_NUMBER, mobile)
            editor.apply()
        }

    var earningPercentage: String?
        get() = sharedPref.getString(EarningPercentage, "")
        set(earning_percentage) {
            editor.putString(EarningPercentage, earning_percentage)
            editor.apply()
        }

    var emailId: String?
        get() = sharedPref.getString(EMAIL_ID, "")
        set(emailId) {
            editor.putString(EMAIL_ID, emailId)
            editor.apply()
        }

    var gender: Int
        get() = sharedPref.getInt(GENDER, 0)
        set(gender) {
            editor.putInt(GENDER, gender)
            editor.apply()
        }

    var profileAvatar: String?
        get() = sharedPref.getString(PROFILE_AVATAR, "")
        set(avatar) {
            editor.putString(PROFILE_AVATAR, avatar)
            editor.apply()
        }

    var authToken: String?
        get() = sharedPref.getString(AUTH_TOKEN, "")
        set(authToken) {
            editor.putString(AUTH_TOKEN, authToken)
            editor.apply()
        }

    var refreshToken: String?
        get() = sharedPref.getString(RefreshToken_TOKEN, "")
        set(refreshToken) {
            editor.putString(RefreshToken_TOKEN, refreshToken)
            editor.apply()
        }


    fun logOutStudent() {
        val lang = SharedPref.instance.checkLanguage
        editor.clear().apply()
        SharedPref.instance.checkLanguage = lang!!
    }
}
