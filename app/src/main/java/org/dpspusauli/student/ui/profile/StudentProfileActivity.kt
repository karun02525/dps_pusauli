package org.dpspusauli.student.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_student_profile.*
import org.dpspusauli.R
import org.dpspusauli.student.model.StudentModel
import org.dpspusauli.student.model.TeacherModel
import org.dpspusauli.student.mvvm.ProfileViewModel
import org.dpspusauli.student.ui.profile.fragments.ParentInfoFragment
import org.dpspusauli.student.ui.profile.fragments.StudentInfoFragment
import org.dpspusauli.student.ui.profile.fragments.TeacherInfoFragment
import org.dpspusauli.utils.log
import org.dpspusauli.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class StudentProfileActivity : AppCompatActivity() {
    private val viewModel: ProfileViewModel by viewModel()
    var student: StudentModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)

        setupViewModel()
        hideShowProgress(true)
        val studentId=intent.extras?.getString("student_id")?:""
        viewModel.getProfileDetails(studentId)

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupViewModel() {
        viewModel.studentData.observe(this, Observer {
            log("Students Data $it")
            viewModel.getTeacher(it.classes.id)
            student=it
        })

        viewModel.teacherData.observe(this, Observer {
            hideShowProgress(false)
            initViewPage(it)
        })

        viewModel.errorMsg.observe(this, Observer {
            hideShowProgress(false)
            cardTab.visibility=View.INVISIBLE
            toast(it)
            onBackPressed()
        })
    }

    @SuppressLint("SetTextI18n")
    private fun initViewPage(teacher: TeacherModel) {
        cardTab.visibility=View.VISIBLE
        tabViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> StudentInfoFragment(student)
                    1 -> ParentInfoFragment(student)
                    2 -> TeacherInfoFragment(teacher)
                    else -> StudentInfoFragment(student)
                }
            }
        }
   //     tabViewPager.currentItem = 0
 //       tabViewPager.offscreenPageLimit = 3
        TabLayoutMediator(tabLayout, tabViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Students Info"
                1 -> "Parents Info"
                2 -> "Class Teacher"
                else -> {
                    null
                }
            }
        }.attach()

    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility = View.GONE
    }
}