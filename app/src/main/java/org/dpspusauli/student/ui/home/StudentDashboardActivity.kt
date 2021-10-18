package org.dpspusauli.student.ui.home
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.dpspusauli.R
import org.dpspusauli.student.model.StudentModel
import org.dpspusauli.student.mvvm.ParentDashboardViewModel
import org.dpspusauli.student.ui.home.fragment.*
import org.dpspusauli.utils.ParentSharedPref
import org.dpspusauli.utils.log
import org.dpspusauli.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class StudentDashboardActivity : AppCompatActivity() {
    private val viewModel: ParentDashboardViewModel by viewModel()
    private val spParent by lazy { ParentSharedPref.instance }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setupViewModel()

        hideShowProgress(true)
        viewModel.getStudentsWithParentId("67987")

    }
    private fun initLoadViewPager() {
        with(main_viewpager) {
            adapter = MainPagerAdapter(supportFragmentManager)
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    main_bottom_navigation.menu.getItem(position).isChecked = true
                }
            })
        }

        main_bottom_navigation.getOrCreateBadge(R.id.action_notifications).apply {
            backgroundColor = Color.RED
            badgeTextColor = Color.WHITE
            maxCharacterCount = 3
            number = 103
            isVisible = true
        }

        main_bottom_navigation.getOrCreateBadge(R.id.action_news).apply {
            backgroundColor = Color.RED
            isVisible = true
        }



        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home ->{
                    main_viewpager.currentItem = 0
                }
                R.id.action_news -> {
                    main_viewpager.currentItem = 1
                }
                R.id.action_notifications -> {
                    main_viewpager.currentItem = 3
                }
                R.id.action_more -> {
                    main_viewpager.currentItem = 4
                }
            }
            true
        }
    }


    inner class MainPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 ->  HomeFragment.instance()
                1 -> NewsFragment()
                2 -> AttendanceFragment()
                3 -> MoreFragment.instance()
                else -> ProfileFragment()
            }
        }

        override fun getCount() = 4
    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }


    private fun setupViewModel() {
        viewModel.studentList.observe(this, Observer {
            hideShowProgress(false)
            log("TAGS => Parent Login Data:   $it")
            updateSharePref(it)
        })


        viewModel.errorMsg.observe(this, Observer {
            initLoadViewPager()
            hideShowProgress(false)
            log("TAGS => Parent/Teacher Login Error :   $it")
            toast(it)
        })
    }

    private fun updateSharePref(it: List<StudentModel>) {
        if(!it.isNullOrEmpty()) {
            spParent.studentArray = Gson().toJson(it)
        }
        initLoadViewPager()
    }

}
