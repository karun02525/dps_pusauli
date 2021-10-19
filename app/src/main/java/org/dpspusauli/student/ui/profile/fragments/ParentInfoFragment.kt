package org.dpspusauli.student.ui.profile.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_profile_details.view.*
import kotlinx.android.synthetic.main.fragment_parent_info.*
import org.dpspusauli.R
import org.dpspusauli.network.Const
import org.dpspusauli.student.model.StudentModel

class ParentInfoFragment(val student: StudentModel?) : Fragment() {
    private val list: ArrayList<ParentInfoModel> = arrayListOf()
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

        student?.run {
            Picasso.get()
                .load("${Const.BASE_URL}/${parentAvatar}")
                .into(iv_parent_pic, object : Callback {
                    override fun onSuccess() {}
                    override fun onError(e: Exception?) {
                        iv_parent_pic.setImageResource(R.drawable.profile_pic)
                    }
                })

            tv_parent_name.text= "$fatherTitle $fatherName"
            tv_sname.text= "$motherTitle $motherName"
            list.run {
                add(ParentInfoModel("Documents Number", parentDocId))
                add(ParentInfoModel("Mobile number", mobile ?: ""))
                add(ParentInfoModel("email", email ?: ""))
                add(ParentInfoModel("address", address ?: ""))
                add(ParentInfoModel("Post Office", postOffice ?: ""))
                add(ParentInfoModel("Dist", distc ?: ""))
                add(ParentInfoModel("State", state ?: ""))
            }
        }
        val mAdapter = ParentInfoAdapter(list)
        rv_ParentInfo.adapter = mAdapter
    }
}

data class ParentInfoModel(val key: String, val value: String)
class ParentInfoAdapter(var list: List<ParentInfoModel> = listOf()) :
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
        fun bindItems(model: ParentInfoModel) {
            itemView.run {
                tv_title.text = model.key
                tv_name.text = model.value
            }
        }
    }


}
