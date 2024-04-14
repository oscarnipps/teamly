package com.app.members

import com.app.members_domain.common.Resource
import com.app.members_domain.model.Member
import com.app.members_domain.usecase.GetMembersUseCase
import kotlinx.coroutines.flow.Flow

class FakeGetMemberUseCase : GetMembersUseCase {

    override suspend fun getMembers(): Flow<Resource<List<Member>>> {
        TODO("Not yet implemented")
    }
}