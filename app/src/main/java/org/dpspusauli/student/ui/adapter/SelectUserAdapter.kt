package org.dpspusauli.student.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_select_user.view.*
import org.dpspusauli.R
import org.dpspusauli.network.Const
import org.dpspusauli.student.model.StudentModel
import org.dpspusauli.student.ui.home.fragment.HomeFragment.Companion.checkedPosition


class SelectUserAdapter(var list: MutableList<StudentModel> = mutableListOf(), var listener: ItemClickListener) :
    RecyclerView.Adapter<SelectUserAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClicked(repos: StudentModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_select_user, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        val selectSign = itemView.btnApply
        val mCnt = itemView.context

        @SuppressLint("SetTextI18n")
        fun bindItems(model: StudentModel) {

            itemView.run {
                Picasso.get()
                    .load("${Const.ImageBaseUrl}/${model.studentAvatar}")
                    .into(ivProfileUser, object : Callback {
                        override fun onSuccess() {}
                        override fun onError(e: Exception?) {
                            ivProfileUser.setImageResource(R.drawable.profile_pic)
                        }
                    })

                val rollMess: String = if (model.rollno == 0) {
                    "Pending"
                }else{
                    ""+model.rollno
                }

                tvUserContactName.text = model.fname + " " + model.lname
                tvNumber.text = "Class:  ${model.classes.name?:"---"},Roll No: $rollMess"
            }

            if (checkedPosition == -1) {
                selectSign.visibility = View.GONE
                model.isActive = true
            } else {
                if (checkedPosition == adapterPosition) {
                    selectSign.visibility = View.VISIBLE
                    model.isActive = true
                } else {
                    selectSign.visibility = View.GONE
                    model.isActive = false
                }
            }

            itemView.setOnClickListener {
                listener.onItemClicked(model)
                model.isActive = true
                selectSign.visibility = View.VISIBLE
                if (checkedPosition != adapterPosition) {
                    notifyItemChanged(checkedPosition)
                    checkedPosition = adapterPosition
                }
            }
        }

    }
}