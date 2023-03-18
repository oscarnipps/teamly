package com.app.members_data.mappers

import com.app.teamly.database.members.MemberWithTeams
import com.app.members_data.models.MembersDto
import com.app.members_domain.model.Member
import com.app.teamly.database.members.entity.MemberEntity
import javax.inject.Inject

class MembersMapper @Inject constructor() {

    fun toMembers(memberWithTeams: List<MemberWithTeams>): List<Member> {
        return memberWithTeams.map { item ->
            Member(
                item.member.imageUrl,
                item.member.name,
                item.member.email,
                item.teams.size
            )
        }
    }

    fun toMemberEntity(membersDto: MembersDto): List<MemberEntity> {
        return membersDto.memberDataDto.map { item ->
            MemberEntity(
                item.id.toString(),
                "${item.firstName} ${item.lastName}",
                item.email,
                item.avatar
            )
        }
    }
}