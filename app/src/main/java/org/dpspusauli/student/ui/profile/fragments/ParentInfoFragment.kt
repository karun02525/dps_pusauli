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
import kotlinx.android.synthetic.main.fragment_parent_info.*
import org.dpspusauli.R
import org.dpspusauli.student.model.ProfileStudent
import org.dpspusauli.student.model.StudentsInfoModel

class ParentInfoFragment : Fragment() {

    private var student: ProfileStudent? = null

    companion object {
        @JvmStatic
        fun instance(student: ProfileStudent?) =
            ParentInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("Student", student)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            student = it.getParcelable("Student")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_info, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_parent_name.text= student?.fatherFname +" "+ student?.fatherLname
        tv_sname.text= student?.fatherSname?:""
        Glide.with(this).load(student?.parent_avatar?:"")
            .placeholder(R.drawable.ic_user)
            .into(iv_parent_pic)

        val list: ArrayList<StudentsInfoModel> = arrayListOf()
        list.run {
            add(StudentsInfoModel("Parent Id", student?.parentId?:""))
            add(StudentsInfoModel("Parent Occupation", student?.parentOccupation?:""))
            add(StudentsInfoModel("Mother first name", student?.motherFname?:""))
            add(StudentsInfoModel("Mother last name", student?.motherLname?:""))
            add(StudentsInfoModel("Mother  surname", student?.motherSname?:""))
            add(StudentsInfoModel("Mobile number",  student?.phone?:""))
            add(StudentsInfoModel("email",  student?.email?:""))
            add(StudentsInfoModel("address", student?.address?:""))
            add(StudentsInfoModel("Post Office",  student?.postOffice?:""))
            add(StudentsInfoModel("Dist",  student?.dist?:""))
            add(StudentsInfoModel("State", student?.state?:""))
            add(StudentsInfoModel("Country", student?.country?:""))
        }


        val mAdapter = ParentInfoAdapter(list)
        rv_ParentInfo.adapter = mAdapter
    }


}

class ParentInfoAdapter(var list: List<StudentsInfoModel> = listOf()) :
    RecyclerView.Adapter<ParentInfoAdapter.ViewHolder>() {

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
