package com.app.members_domain.repo

import com.app.members_domain.common.Resource
import com.app.members_domain.model.Member
import com.app.members_domain.model.MemberDetails
import kotlinx.coroutines.flow.Flow

interface MembersRepo {

    fun getMembers() : Flow<Resource<List<Member>>>

    fun getTeamsWithMember() : Flow<Resource<MemberDetails>>

    //fun upsertMembers
    //fun getMembersFromDatabase
}