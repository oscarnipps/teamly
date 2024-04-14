package com.app.members_data.repo

import com.app.members_data.api.MemberService
import com.app.members_data.mappers.MembersMapper
import com.app.members_domain.common.Resource
import com.app.members_domain.model.Member
import com.app.members_domain.model.MemberDetails
import com.app.members_domain.repo.MembersRepo
import com.app.teamly.database.members.dao.MemberDao
import com.app.teamly.database.members.entity.MemberEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class MembersRepoImpl @Inject constructor(
    private val memberService: MemberService,
    private val memberDao: MemberDao,
    private val membersMapper: MembersMapper
) : MembersRepo {

    override fun getMembers(): Flow<Resource<List<Member>>> {
        return flow {

            emit(Resource.Loading())

            try {
                val membersDtoFromApi = memberService.getMembersFromApi()

                val memberEntityList = membersMapper.toMemberEntity(membersDtoFromApi)

                upsertMembers(memberEntityList)

                val membersWithTeams = memberDao.getMembersWithTeams()

                val memberList = membersMapper.toMembers(membersWithTeams)

                emit(Resource.Success(memberList))

            } catch (httpException: HttpException) {
                emit(Resource.Error("error getting members from server"))

            } catch (ioException: IOException) {
                emit(Resource.Error("unknown error occurred"))
            }
        }
    }

    private suspend fun upsertMembers(memberEntityList: List<MemberEntity>?) {
            memberEntityList?.forEach { memberEntity ->
                if (memberDao.getMember(memberEntity.id) == 0) {
                    Timber.d("member with id { ${memberEntity.id} } does not exist so, inserting ...")

                    memberDao.insertMember(memberEntity)

                    return@forEach
                }

                Timber.d("member with id { ${memberEntity.id} } already exists so, updating ...")
                memberDao.updateMember(memberEntity)
            }
    }


    override fun getTeamsWithMember(): Flow<Resource<MemberDetails>> {
        TODO("Not yet implemented")
    }

}