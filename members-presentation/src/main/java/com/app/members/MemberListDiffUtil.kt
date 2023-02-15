package com.app.members

import androidx.recyclerview.widget.DiffUtil
import com.app.members_domain.model.Member

class MemberListDiffUtil(
    private val oldList: List<Member>,
    private val newList: List<Member>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //todo change check to use id instead
        return oldList[oldItemPosition].email ==  newList[newItemPosition].email
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] ==  newList[newItemPosition]
    }
}