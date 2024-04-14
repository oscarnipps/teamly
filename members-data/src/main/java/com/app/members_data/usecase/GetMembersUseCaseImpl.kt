package com.app.members_data.usecase

import com.app.members_domain.common.Resource
import com.app.members_domain.model.Member
import com.app.members_domain.repo.MembersRepo
import com.app.members_domain.usecase.GetMembersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMembersUseCaseImpl @Inject constructor(private val membersRepo: MembersRepo) :
    GetMembersUseCase {

    override suspend fun getMembers(): Flow<Resource<List<Member>>> {
        return membersRepo.getMembers()
    }

    suspend fun getMembersLimit(name : String) : Int {
        if (name.isEmpty()) {
            return 5
        }

        return 10
    }

}