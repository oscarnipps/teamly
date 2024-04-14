package com.app.members_domain.usecase

import com.app.members_domain.common.Resource
import com.app.members_domain.model.Member
import kotlinx.coroutines.flow.Flow

interface GetMembersUseCase {

   suspend fun getMembers(): Flow<Resource<List<Member>>>
}