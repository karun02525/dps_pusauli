package org.dpspusauli.student.ui.home.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.popwoot.carouselbanner.Banner
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_select_student.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.dpspusauli.R
import org.dpspusauli.network.Const
import org.dpspusauli.student.model.MenuModel
import org.dpspusauli.student.model.StudentModel
import org.dpspusauli.student.ui.adapter.MenuAdapter
import org.dpspusauli.student.ui.adapter.SelectUserAdapter
import org.dpspusauli.student.ui.attendance.AttendanceCalender
import org.dpspusauli.student.ui.profile.StudentProfileActivity
import org.dpspusauli.utils.ImageFactory
import org.dpspusauli.utils.ParentSharedPref


class HomeFragment : Fragment() {
    private val sp by lazy { ParentSharedPref.instance }
    var list: ArrayList<MenuModel> = arrayListOf()
    private var listStudents = mutableListOf<StudentModel>()
    private var student_id = ""

    companion object {
        var checkedPosition = 0

        @JvmStatic
        fun instance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAGS", "Student Data: " + sp.studentArray)
        if(sp.studentArray !=null) {
            val type = object : TypeToken<List<StudentModel?>?>() {}.type
            if(type !=null)
            listStudents = Gson().fromJson(sp.studentArray, type)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        banner()

        marqueeText.text = createMarqueeText();
        marqueeText.requestFocus()

        initRecy()
    }

    private fun initRecy() {
        list.add(MenuModel("Profile", R.drawable.ic_user))
        list.add(MenuModel("Subject", R.drawable.ic_open_book))
        list.add(MenuModel("Notice", R.drawable.ic_noticeboard))
        list.add(MenuModel("Attendance", R.drawable.ic_attendance))
        list.add(MenuModel("Application", R.drawable.ic_report))
        list.add(MenuModel("Fee", R.drawable.ic_wallet))
        list.add(MenuModel("Result", R.drawable.ic_result))
        list.add(MenuModel("Bus", R.drawable.ic_bus))


        val mAdapter = MenuAdapter(list, object : MenuAdapter.ItemClickListener {
            override fun onItemClicked(repos: MenuModel, pos: Int) {
                openDetails(repos, pos)
            }
        })
        rv_menu.adapter = mAdapter

        if(listStudents.size !=0) {
            selectUserCall(listStudents[0])
        }
        btnOpenDialog.setOnClickListener {
            openDialog()
        }
    }


    private fun openDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_select_student, null)
        val dig = Dialog(context!!, R.style.MaterialDialogSheet)
        dig.setContentView(view)
        dig.setCancelable(true)
       dig.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dig.window!!.setGravity(Gravity.BOTTOM)
        dig.show()


        val mAdapter = SelectUserAdapter(
            listStudents,
            object : SelectUserAdapter.ItemClickListener {
                override fun onItemClicked(repos: StudentModel) {
                    dig.dismiss()
                    selectUserCall(repos)
                }
            })
        view.rv_user.adapter = mAdapter
        view.btnContClose.setOnClickListener { dig.dismiss() }
    }


    @SuppressLint("SetTextI18n")
    fun selectUserCall(model: StudentModel?) {
        model?.run {
            student_id = id
            Picasso.get()
                .load("${Const.BASE_URL}/${studentAvatar}")
                .into(profile_image, object : Callback {
                    override fun onSuccess() {}
                    override fun onError(e: Exception?) {
                        profile_image.setImageResource(R.drawable.profile_pic)
                    }
                })

            tv_name.text = "$fname $lname"
            val rollMess: String = if (rollno == 0) {
                "Pending"
            }else{
                ""+rollno
            }
            tv_sec.text = "Class: ${classes.name ?: "---"},Roll No: $rollMess"
        }
    }

    private fun createMarqueeText(): String {
        val text = StringBuilder()
        text.append("Dear parents Greetings of the day Hope you all are doing well. Stay healthy & Safe")
        text.append("This is to inform you that school has done some changes in the *FEE payments*…")
        return text.toString()
    }

    private fun banner() {
        val list = ArrayList<String>()
        Banner.init(ImageFactory())

        list.add("https://www.voicesofyouth.org/sites/voy/files/images/2019-03/school.jpg")
        list.add("https://mk0digitallearn7ttjx.kinstacdn.com/wp-content/uploads/2021/02/Bihar-Schools.jpg")
        list.add("https://resize.indiatvnews.com/en/resize/newbucket/715_-/2020/12/school-reopen-1608305639.jpg")
        list.add("https://images.indianexpress.com/2021/01/pic-3-5-1.jpg")
        list.add("https://img.etimg.com/thumb/width-640,height-480,imgsize-226937,resizemode-1,msid-78175438/news/politics-and-nation/centre-red-flags-school-dropout-rate-vacant-seats-in-bihar-girls-schools/school-girls-bccl-new.jpg")


        banner.setOnCarouselItemChangeListener {
            //                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
        }


        banner.setOnCarouselItemClickListener { position, url ->
            //   Toast.makeText(this@MainActivity, url, Toast.LENGTH_LONG).show()
        }

        //banner.initBanner(list)
        banner.initBanner(list) //Full screen banner no 2

    }


    private fun openDetails(repos: MenuModel, pos: Int) {
        if (pos == 0) {
            requireContext().startActivity(
                Intent(requireContext(), StudentProfileActivity::class.java)
                    .putExtra("student_id", student_id)
            )
        } else if (pos == 3) {
            requireContext().startActivity(
                Intent(requireContext(), AttendanceCalender::class.java)
                    .putExtra("student_id", student_id)
            )
        }
    }
}

