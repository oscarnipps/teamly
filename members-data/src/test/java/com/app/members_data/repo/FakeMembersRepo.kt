package com.app.members_data.repo

import com.app.members_domain.common.Resource
import com.app.members_domain.model.Member
import com.app.members_domain.model.MemberDetails
import com.app.members_domain.repo.MembersRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMembersRepo : MembersRepo {


    override fun getMembers(): Flow<Resource<List<Member>>> {
        return flow {
            emit(
                Resource.Success(
                    listOf(
                        Member("profile/john.jpg", "john stone", "john@gmail.com", 0),
                        Member("profile/dave.jpg", "dave chaps", "dave@gmail.com", 0),
                        Member("profile/obi.jpg", "obi kenobi", "obi@gmail.com", 0)
                    )
                )
            )
        }
    }

    override fun getTeamsWithMember(): Flow<Resource<MemberDetails>> {
        TODO("Not yet implemented")
    }
}