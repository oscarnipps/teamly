package com.app.members_domain.usecase

import com.app.members_domain.common.Resource
import com.app.members_domain.model.Member
import com.app.members_domain.repo.MembersRepo
import kotlinx.coroutines.flow.Flow

class GetMembersUseCase(private val membersRepo: MembersRepo) {

    suspend operator fun  invoke() : Flow<Resource<List<Member>>> {
        return membersRepo.getMembers()
    }

}