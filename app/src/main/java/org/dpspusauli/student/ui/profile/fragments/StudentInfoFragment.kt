package org.dpspusauli.student.ui.profile.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_profile_details.view.*
import kotlinx.android.synthetic.main.fragment_student_info.*
import org.dpspusauli.R
import org.dpspusauli.network.Const
import org.dpspusauli.student.model.Student
import org.dpspusauli.student.model.StudentModel


class StudentInfoFragment(var student: Student?) : Fragment() {
   private val list: ArrayList<StudentsInfoModel> = arrayListOf()

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

        student?.run {
            Picasso.get()
                .load("${Const.ImageBaseUrl}/${studentPic}")
                .into(iv_stu_pic, object : Callback {
                    override fun onSuccess() {}
                    override fun onError(e: Exception?) {
                        iv_stu_pic.setImageResource(R.drawable.profile_pic)
                    }
                })

            tv_stu_name.text = "$firstName $lastName"
            val rollMess: String = if (rollNo == 0) {
                "Pending"
            }else{
                ""+rollNo
            }
            tv_sname.text = "Class: ${classes.className ?: "---"},Roll No: $rollMess"

            list.run {
                add(StudentsInfoModel("Roll No", rollNo.toString()))
                add(StudentsInfoModel("Class", classes.className ?: "----"))
                add(StudentsInfoModel("Gender", gender ?: ""))
                add(StudentsInfoModel("Date of birth", dob ?: ""))
                add(StudentsInfoModel("Mobile number", mobile ?: ""))
                add(StudentsInfoModel("email", email ?: ""))
                add(StudentsInfoModel("address", address ?: ""))
                add(StudentsInfoModel("Post Office", pincode ?: ""))
                add(StudentsInfoModel("Dist", dist ?: ""))
                add(StudentsInfoModel("State", state ?: ""))
            }
        }

        val mAdapter = StudentsInfoAdapter(list)
        rv_StuInfo.adapter = mAdapter

    }


}

data class StudentsInfoModel(val key: String, val value: String)
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
