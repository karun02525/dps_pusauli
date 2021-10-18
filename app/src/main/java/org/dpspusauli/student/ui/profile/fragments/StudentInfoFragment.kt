package org.dpspusauli.student.ui.profile.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_profile_details.view.*
import kotlinx.android.synthetic.main.fragment_student_info.*
import org.dpspusauli.R
import org.dpspusauli.student.model.ProfileClassInfo
import org.dpspusauli.student.model.ProfileStudent
import org.dpspusauli.student.model.StudentsInfoModel


class StudentInfoFragment : Fragment() {
    private var student: ProfileStudent? = null
    private var classInfo: ProfileClassInfo? = null

    companion object {
        @JvmStatic
        fun instance(student: ProfileStudent?, classInfo: ProfileClassInfo?) =
            StudentInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("Student", student)
                    putParcelable("class_info", classInfo)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            student = it.getParcelable("Student")
            classInfo = it.getParcelable("class_info")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_info, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_stu_name.text= student?.fname +" "+ student?.lname
        tv_sname.text= student?.surname?:""
        Glide.with(this).load(student?.student_avatar?:"")
            .placeholder(R.drawable.ic_user)
            .into(iv_stu_pic)

        val list: ArrayList<StudentsInfoModel> = arrayListOf()
        list.run {
            add(StudentsInfoModel("Roll No", classInfo?.roll_no?:"----"))
            add(StudentsInfoModel("Class", classInfo?.class_name?:"----"))
            add(StudentsInfoModel("SEC",  classInfo?.section?:"----"))
            add(StudentsInfoModel("Gender",  student?.gender?:""))
            add(StudentsInfoModel("Date of birth",  student?.dob?:""))
            add(StudentsInfoModel("Mobile number",  student?.phone?:""))
            add(StudentsInfoModel("email",  student?.email?:""))
            add(StudentsInfoModel("address", student?.address?:""))
            add(StudentsInfoModel("Post Office",  student?.postOffice?:""))
            add(StudentsInfoModel("Police Station",  student?.policeStation?:""))
            add(StudentsInfoModel("Dist",  student?.dist?:""))
            add(StudentsInfoModel("State", student?.state?:""))
            add(StudentsInfoModel("Country", student?.country?:""))
        }

        val mAdapter = StudentsInfoAdapter(list)
        rv_StuInfo.adapter = mAdapter
    }


}


class StudentsInfoAdapter(var list: List<StudentsInfoModel> = listOf()) :
    RecyclerView.Adapter<StudentsInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.adapter_profile_details, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: StudentsInfoModel) {
            itemView.run {
                tv_title.text = model.key
                tv_name.text = model.value
            }
        }
    }


}
