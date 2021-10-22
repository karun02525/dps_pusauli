package org.dpspusauli

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.dpspusauli.student.ui.home.StudentDashboardActivity
import org.dpspusauli.utils.SharedPref
import org.dpspusauli.utils.TeacherSharedPref
import org.dpspusauli.utils.startNewActivityFinish

class SplashActivity : AppCompatActivity() {
    private val spParent by lazy { SharedPref.instance }
    private val spTeacher by lazy { TeacherSharedPref.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startNewActivityFinish(StudentDashboardActivity::class.java)


 /*       Handler().postDelayed({
            when {
                spParent.isLoginStatus == 1 -> {
                    startNewActivityFinish(StudentDashboardActivity::class.java)
                }
                else -> {
                    startNewActivityFinish(LoginActivity::class.java)
                }
            }
        }, 500)*/


    }
}