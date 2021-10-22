package org.dpspusauli.student.ui.profile.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_profile_details.view.*
import kotlinx.android.synthetic.main.fragment_student_info.*
import kotlinx.android.synthetic.main.fragment_teacher_info.*
import org.dpspusauli.R
import org.dpspusauli.network.Const
import org.dpspusauli.student.model.ProfileTeacher
import org.dpspusauli.student.model.TeacherModel

class TeacherInfoFragment(val teacher: TeacherModel?) : Fragment() {
    val list: ArrayList<TeacherInfoModel> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teacher_info, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (teacher == null) {
            main_ui_hide.visibility = View.GONE
            empty_data.visibility = View.VISIBLE
        } else {
            main_ui_hide.visibility = View.VISIBLE
            empty_data.visibility = View.GONE
            teacher.run {
                tv_teacher_name.text = "$fname $lname"
                tv_tsname.text = parentName

                Picasso.get()
                    .load("${Const.ImageBaseUrl}/${teacherAvatar}")
                    .into(iv_teacher_pic, object : Callback {
                        override fun onSuccess() {}
                        override fun onError(e: Exception?) {
                            iv_teacher_pic.setImageResource(R.drawable.profile_pic)
                        }
                    })

                list.run {
                    add(TeacherInfoModel("Date of birth", dob ?: ""))
                    add(TeacherInfoModel("Mobile number", mobile ?: ""))
                    add(TeacherInfoModel("Email ID ", email ?: ""))
                    add(TeacherInfoModel("Qualification", qualification ?: ""))
                }
            }

            val mAdapter = TeacherInfoAdapter(list)
            rv_TeacherInfo.adapter = mAdapter
        }
    }


}


data class TeacherInfoModel(val key: String, val value: String)

class TeacherInfoAdapter(var list: List<TeacherInfoModel> = listOf()) :
    RecyclerView.Adapter<TeacherInfoAdapter.ViewHolder>() {

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
        fun bindItems(model: TeacherInfoModel) {
            itemView.run {
                tv_title.text = model.key
                tv_name.text = model.value
            }
        }
    }


}
