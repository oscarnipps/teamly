package com.app.members

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.members_domain.model.Member
import com.app.teamly.members_presentation.R
import com.app.teamly.members_presentation.databinding.MemberListItemBinding
import com.bumptech.glide.Glide

class MemberListAdapter(val memberItemListener: MemberItemListener) :
    ListAdapter<Member, MemberListAdapter.MemberListViewHolder>(MemberListItemDiffCallback()) {

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
        val currentMember = getItem(position)
        holder.bindMember(currentMember)
    }

    interface MemberItemListener {
        fun onMemberItemClicked(currentItem: Member)
    }

    private class MemberListItemDiffCallback : DiffUtil.ItemCallback<Member>() {
        override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem == newItem
        }
    }

    inner class MemberListViewHolder(private val itemBinding: MemberListItemBinding) :
        ViewHolder(itemBinding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bindMember(member: Member) {
            itemBinding.member = member
            itemBinding.executePendingBindings()
        }

        override fun onClick(p0: View?) {
            val currentItem = getItem(bindingAdapterPosition)
            memberItemListener.onMemberItemClicked(currentItem)
        }

    }
}