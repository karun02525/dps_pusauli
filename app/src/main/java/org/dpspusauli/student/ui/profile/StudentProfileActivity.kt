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
import org.dpspusauli.student.model.ProfileData
import org.dpspusauli.student.mvvm.ProfileViewModel
import org.dpspusauli.student.ui.profile.fragments.ParentInfoFragment
import org.dpspusauli.student.ui.profile.fragments.StudentInfoFragment
import org.dpspusauli.student.ui.profile.fragments.TeacherInfoFragment
import org.dpspusauli.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class StudentProfileActivity : AppCompatActivity() {
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)

        setupViewModel()
        hideShowProgress(true)
        val student_id=intent.extras?.getString("student_id")?:""
        viewModel.getProfileDetails(student_id)

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupViewModel() {
        viewModel.data.observe(this, Observer {
            initViewPage(it)
            hideShowProgress(false)
        })
        viewModel.errorMsg.observe(this, Observer {
            hideShowProgress(false)
            cardTab.visibility=View.INVISIBLE
            toast(it)
            onBackPressed()
        })
    }

    @SuppressLint("SetTextI18n")
    private fun initViewPage(data: ProfileData) {
        cardTab.visibility=View.VISIBLE
        tabViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> StudentInfoFragment.instance(data.student,data.class_info)
                    1 -> ParentInfoFragment.instance(data.student)
                    2 -> TeacherInfoFragment.instance(data.teacher)
                    else -> StudentInfoFragment.instance(data.student, data.class_info)
                }
            }
        }
        tabViewPager.currentItem = 0
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