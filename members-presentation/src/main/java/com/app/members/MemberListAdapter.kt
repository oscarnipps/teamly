package com.app.members

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.members_domain.model.Member
import com.app.teamly.members_presentation.R
import com.app.teamly.members_presentation.databinding.MemberListItemBinding
import com.bumptech.glide.Glide

class MemberListAdapter(private val members :List<Member>) : RecyclerView.Adapter<MemberListAdapter.MemberListViewHolder>() {

    //private var members = emptyList<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberListViewHolder {
        val binding: MemberListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.member_list_item,
            parent,
            false
        )

        return MemberListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberListViewHolder, position: Int) {
        val currentMember = members[position]
        holder.bindMember(currentMember)
    }

    override fun getItemCount(): Int {
        return members.size
    }

    inner class MemberListViewHolder(private val itemBinding: MemberListItemBinding) :
        ViewHolder(itemBinding.root) {

        fun bindMember(member: Member) {
            itemBinding.memberName.text = member.name
            itemBinding.memberEmail.text = member.email
            itemBinding.teamsCount.text = member.teamsCount.toString()
            Glide.with(itemView.context)
                .load(member.imageUrl)
                .into(itemBinding.memberImage);
        }


    }


}