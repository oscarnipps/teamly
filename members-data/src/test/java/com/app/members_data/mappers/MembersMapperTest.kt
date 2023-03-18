package com.app.members_data.mappers

import com.app.members_data.models.MemberDataDto
import com.app.members_data.models.MembersDto
import com.app.teamly.database.members.entity.MemberEntity
import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class MembersMapperTest {

    private lateinit var memberDataDtoList : List<MemberDataDto>
    private lateinit var memberEntityList : List<MemberEntity>

    @Before
    fun setUp() {
        memberEntityList = listOf(
            MemberEntity("1", "john stone", "john@gmail.com", "profile/john.jpg"),
            MemberEntity("2", "dave chaps", "dave@gmail.com", "profile/dave.jpg"),
            MemberEntity("3", "obi kenobi", "obi@gmail.com", "profile/obi.jpg")
        )

        memberDataDtoList = listOf(
            MemberDataDto("profile/john.jpg", "john@gmail.com", "john", 1, "stone"),
            MemberDataDto("profile/dave.jpg", "dave@gmail.com", "dave", 2, "chaps"),
            MemberDataDto("profile/obi.jpg", "obi@gmail.com", "obi", 3, "kenobi")
        )
    }

    @Test
    fun `valid MembersDto is mapped to non-empty MemberEntity list`() {
        val membersDto = MembersDto(memberDataDtoList)

        val actualMemberEntityList = MembersMapper().toMemberEntity(membersDto)

        Truth.assertThat(actualMemberEntityList).isNotEmpty()
    }

    @Test
    fun `valid MembersDto is mapped to MemberEntity list`() {
        val membersDto = MembersDto(memberDataDtoList)

        val actualMemberEntityList = MembersMapper().toMemberEntity(membersDto)

        Truth.assertThat(actualMemberEntityList).isEqualTo(memberEntityList)
    }
}
